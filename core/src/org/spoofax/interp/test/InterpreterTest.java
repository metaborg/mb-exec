/*
 * Created on 05.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interp.test;

import java.io.IOException;

import org.spoofax.interp.FatalError;
import org.spoofax.interp.Interpreter;
import org.spoofax.interp.Tools;

import aterm.ATerm;
import aterm.pure.ATermImpl;

import junit.framework.TestCase;
import junit.framework.TestResult;

public class InterpreterTest extends TestCase {

    Interpreter itp;

    @Override
    protected void setUp() throws Exception {

        itp = new Interpreter();
        super.setUp();
    }

    public void testBuildInt() {
        interpTest("build_int", "1", "5");
    }

    public void testBuildString() {
        interpTest("build_string", "1", "\"a\"");
    }

    public void testBuildTuple() {
        interpTest("build_tuple", itp.makeTerm("1"), itp.makeTuple("[2, 3]"));
    }

    public void testBuildList1() {
        interpTest("build_list_1", itp.makeTerm("1"), itp.makeList("[]"));
    }

    public void testBuildList2() {
        interpTest("build_list_2", itp.makeTerm("1"), itp.makeList("[1,2,3]"));
    }

    public void testMatchInt1() {
        interpTest("match_int_1", "2", "2");
    }

    public void testMatchString1() {
        interpTest("match_string_1", "\"abc\"", "\"abc\"");
    }

    public void testMatchInt2() {
        interpTestFail("match_int_2", "3");
    }

    public void testMatchString2() {
        interpTestFail("match_string_2", "\"abc\"");
    }

    public void testMatchTuple1() {
        interpTest("match_tuple_1", itp.makeTuple("[1,2]"), itp.makeTerm("1"));
    }

    public void testMatchTuple2() {
        interpTest("match_tuple_2", itp.makeTuple("[1,2]"), itp.makeTerm("2"));
    }

    public void testMatchTuple3() {
        interpTest("match_tuple_3", itp.makeTuple("[2,2]"), itp.makeTerm("2"));
    }

    public void testMatchTuple4() {
        interpTestFail("match_tuple_4", itp.makeTuple("[2,3]"));
    }

    public void testMatchList1() {
        interpTest("match_list_1", "Cons(1, Cons(2, Nil))", "1");
    }

    public void testMatchList2() {
        interpTest("match_list_2", "Cons(1, Cons(2, Nil))", "2");
    }

    public void testMatchList3() {
        interpTest("match_list_3", "Cons(2, Cons(2, Nil))", "2");
    }

    public void testMatchList4() {
        interpTestFail("match_list_4", "Cons(2, Cons(3, Nil))");
    }

    public void testMatchAndBuild1() {
        interpTest("match_and_build_1", "1", "1");
    }

    public void testMatchAndBuild2() {
        interpTestFail("match_and_build_2", "1");
    }

    public void interpTestFail(String test, ATerm input) {
        assertFalse(runInterp(test, input));
    }

    public void interpTestFail(String test, String input) {
        ATerm t = itp.makeTerm(input);
        interpTestFail(test, t);
    }

    public void interpTest(String test, String input, String output) {
        ATerm i = itp.makeTerm(input);
        ATerm o = itp.makeTerm(output);
        interpTest(test, i, o);
    }

    public void interpTest(String test, ATerm input, ATerm output) {
        assertTrue(runInterp(test, input));
        ATermImpl x = (ATermImpl) output;
        ATermImpl y = (ATermImpl) itp.getCurrent();
        assertTrue(x.getFactory() == y.getFactory());
        System.out.println(y + " / " + y.getType() + " / " + y.getClass()
                + " / " + y.getChildCount() + " / " + y.getFactory());
        System.out.println(x + " / " + x.getType() + " / " + x.getClass()
                + " / " + x.getChildCount() + " / " + x.getFactory());
        System.out.println(itp.getCurrent().match(output));
        assertTrue(itp.getCurrent().match(output) != null);
    }

    private boolean runInterp(String test, ATerm input) {
        itp.reset();
        try {
            itp.load("/home/karltk/source/oss/spoofax/spoofax/core/tests/data/" + test
                    + ".rtree");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        itp.setCurrent(input);
        try {
            return itp.eval(itp.makeTerm("CallT(SVar(\"main_0_0\"), [], [])"));

        } catch (FatalError e) {
            e.printStackTrace();
            assertTrue("Exception occured", false);
        }
        return false;
    }

    public void testScopeOutOfScope1() {
        interpTestFail("out_of_scope_1", "2");
    }

    public void testScopeMatchInterrupt1() {
        interpTest("match_interrupted_by_scope_1", "2", "2");
    }

    public void testScopeMatchInterrupt2() {
        interpTest("match_interrupted_by_scope_2", "2", "2");
    }

    public void testScopeMatchInterrupt3() {
        interpTest("match_interrupted_by_scope_3", "3", "3");
    }

    public void testScopeMatchInterrupt4() {
        interpTest("match_interrupted_by_scope_4", "4", "4");
    }

    public void testScopeOutOfScope3() {
        interpTestFail("out_of_scope_3", "2");
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(InterpreterTest.class);
    }
    
    public void testChoiceUnbinding() { 
        interpTest("unbinding_in_lchoice",  "2", "3");
    }
    
    public void testChoiceDoNotUnbinding() {
       interpTest("do_not_unbinding_lhs_of_lchoice_if_it_succeeds", itp.makeTuple("[]"), itp.makeTerm("1"));
    }

    public void testLeftChoiceGuard() {
        interpTest("guarded_modifies_current_term", itp.makeTuple("[]"), itp.makeTerm("3"));
    }
    
    public void testLeftChoiceUnbind() {
        interpTestFail("unbinding_of_guard_in_guarded_lchoice", "1");
    }

    public void testCongInt1() {
        interpTest("cong_int_1", "2", "2");
    }
    
    public void testCongInt2() {
        interpTestFail("cong_int_2", "3");
    }
    
    public void testCongString1() {
        interpTest("cong_string_1", "\"foo\"", "\"foo\"");
    }
    
    public void testCongString2() {
        interpTestFail("cong_string_2", "\"foo\"");
    }
    
    public void testCongTuple1() {
        interpTest("cong_tuple_1", itp.makeTuple("[3, 4]"), itp.makeTuple("[3, 4]"));
    }

    public void testCongTuple2() {
        interpTest("cong_tuple_2", itp.makeTuple("[3, 4]"), itp.makeTuple("[4, 5]"));
    }
    
    public void testCongTuple3() {
        interpTestFail("cong_tuple_3", itp.makeTuple("[3, 4]"));
    }
    
    public void testCongTuple4() {
        interpTest("cong_tuple_4", itp.makeTuple("[3, 4]"), itp.makeTuple("[3,5]"));
    }
    
    public void testCongTuple5() {
        interpTestFail("cong_tuple_5", itp.makeTuple("[3, 5]"));
    }

    public void testCongList1() {
        interpTest("cong_list_1", itp.makeList("[]"), itp.makeList("[]"));
    }
    
    public void testCongList2() {
        interpTest("cong_list_2", itp.makeList("[1]"), itp.makeList("[2]"));
    }

    public void testCongList3() {
        interpTest("cong_list_3", itp.makeList("[1,2]"), itp.makeList("[1,3]"));
    }

    public void testCongList4() {
        interpTest("cong_list_4", itp.makeList("[1]"), itp.makeList("[1]"));
    }

    public void testCongList5() {
        interpTest("cong_list_5", itp.makeList("[1,2]"), itp.makeList("[1]"));
    }

    public void testCongList6() {
        interpTestFail("cong_list_6", itp.makeList("[1,2]"));
    }

    public void testCongList7() {
        interpTestFail("cong_list_7", itp.makeList("[1,2]"));
    }

    public void testCongList8() {
        interpTestFail("cong_list_8", itp.makeList("[1,2]"));
    }

    public void testCongList9() {
        interpTest("cong_list_9", itp.makeList("[1,2]"), itp.makeList("[2]"));
    }

    public void testCongList10() {
        interpTest("cong_list_10", itp.makeList("[1,2]"), itp.makeTerm("2"));
    }

    public void testCongList11() {
        interpTestFail("cong_list_11", itp.makeList("[1]"));
    }

    public void testCongDistribute1() {
    }

    /*
  ; ssh-apply-test(|"distributing congruence  1", ([1,2], 3), 
      " rec x(Cons^D(id,  x) + Nil^D) ;; 
      ", [(1,3), (2,3)])
  ; ssh-apply-test(|"distributing congruence  2", ([1,2], 3), 
      " import integers;;
        rec x(Cons^D(add, x) + Nil^D) ;; 
      ", [4, 5])
  ; ssh-apply-test(|"threading congruence  1", ([1,2, 3], 0),
      " rec x(Cons^T(id,  x) + Nil^T) ;; 
      ", ([1, 2, 3], 0))
  ; ssh-apply-test(|"threading congruence  2", ([1,2,3], 0), 
      " import integers;;
        rec x(Cons^T((id, inc), x) + Nil^T) ;; 
      ", ([1, 2, 3], 3))
*/
}

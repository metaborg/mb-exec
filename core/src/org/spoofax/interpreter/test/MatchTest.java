/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

public class MatchTest extends LanguageTest {

    public void testMatchReal3() {
        interpTestFail("match_real_2", itp.makeTerm("2.0"));
    }

    public void testMatchString1() {
        interpTest("match_string_1", "\"abc\"", "\"abc\"");
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

    public void testMatchInt1() {
        interpTest("match_int_1", "2", "2");
    }

    public void testMatchInt2() {
        interpTestFail("match_int_2", "3");
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

    public void testMatchReal1() {
        interpTest("match_real_1", itp.makeTerm("2.0"), itp.makeTerm("2.0"));
    }

    public void testMatchReal2() {
        interpTest("match_real_2", itp.makeTerm("4.0"), itp.makeTerm("4.0"));
    }

    public void testAs1() {
        interpTest("as_1", itp.makeTuple("[1,2]"), itp.makeTuple(itp.makeTuple("[1,2]"), itp.makeTuple("[1,2]")));
    }

    public void testAs2() {
        interpTest("as_2", itp.makeTuple("[1,2]"), itp.makeTuple("[1,1]"));
    }

    public void testGuarded1() {
           interpTest("guarded_1", itp.makeTuple("[]"), itp.makeTerm("1"));
       }

    public void testGuarded2() {
           interpTest("guarded_2", itp.makeTuple("[]"), itp.makeTerm("2"));
       }

    public void testGuarded3() {
           interpTest("guarded_3", itp.makeTuple("[]"), itp.makeTerm("3"));
       }

    public void testGuarded4() {
           interpTest("guarded_4", itp.makeTerm("5"), itp.makeTerm("5"));
       }

    public void testGuarded5() {
           interpTest("guarded_5", itp.makeTuple("[]"), itp.makeTerm("1"));
       }

    public void testGuarded6() {
           interpTest("guarded_6", itp.makeTuple("[]"), itp.makeTerm("1"));
       }

    public void testGuarded7() {
           interpTest("guarded_7", itp.makeTuple("[]"), itp.makeTerm("2"));
       }

    public void testLeftChoiceGuard() {
        interpTest("guarded_modifies_current_term", itp.makeTuple("[]"), itp
                .makeTerm("3"));
    }

    public void testMatchAndBuild1() {
        interpTest("match_and_build_1", "1", "1");
    }

    public void testMatchAndBuild2() {
        interpTestFail("match_and_build_2", "1");
    }

    public void testProject1() {
        interpTest("project_1", itp.makeTuple("[2,3]"), itp.makeTerm("2"));
    }

    public void testProject2() {
        interpTest("project_2", itp.makeTuple("[2,3]"), itp.makeTerm("3"));
    }

}

/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

import aterm.ATerm;

public class ClosureTest extends AbstractLanguageTest {

    public void testClosure1() {
        interpTest("closure_test_1", itp.makeTuple("[]"), itp.makeTerm("1"));
    }

    public void testClosure10() {
        interpTest("closure_test_10", 
                   itp.makeList("[5, 5, 5, 5, 5, 5, 5, 5, 5, 5]"), 
                   itp.makeList("[4, 6, 4, 6, 4, 6, 4, 6, 4, 6]"));
    }

    public void testClosure11() {
           interpTest("closure_test_11", 
                      itp.makeList("[5, 5, 5, 5, 5, 5, 5, 5, 5, 5]"), 
                      itp.makeList("[4, 6, 4, 6, 4, 6, 4, 6, 4, 6]"));
       }

    public void testClosure12() {
           interpTest("closure_test_12", 
                      itp.makeList("[5, 5, 5, 5, 5, 5, 5, 5, 5, 5]"), 
                      itp.makeList("[4, 6, 4, 6, 4, 6, 4, 6, 4, 6]"));
       }

    public void testClosure13() {
           interpTest("closure_test_13", 
                      itp.makeTerm("1"), 
                      itp.makeTerm("4"));
       }

    public void testClosure14() {
        interpTest("closure_test_14", 
                   itp.makeTerm("1"), 
                   itp.makeTerm("6"));
    }

    public void testClosure15() {
        interpTest("closure_test_15", 
                   itp.makeList(itp.makeList("[1,2,3]"), itp.makeList("[4,5,6]")), 
                   itp.makeList(itp.makeList("[2,3,4]"), itp.makeList("[5,6,7]")));
    }

    public void testClosure2() {
        interpTest("closure_test_2", itp.makeTuple("[]"), itp.makeTerm("1"));
    }

    public void testClosure2b() {
        interpTest("closure_test_2b", 
                   itp.makeList("[5, 5, 5, 5, 5, 5, 5, 5, 5, 5]"), 
                   itp.makeList("[3, 3, 3, 3, 3, 3, 3, 3, 3, 3]"));
    }

    public void testClosure2c() {
        interpTest("closure_test_2c", 
                   itp.makeList("[5, 5, 5, 5, 5, 5, 5, 5, 5, 5]"), 
                   itp.makeList("[3, 3, 3, 3, 3, 3, 3, 3, 3, 3]"));
    }

    private ATerm makeRecTuple(int i, String t) {
        if(i == 0)
            return itp.makeTuple("[]");
        return itp.makeTuple(itp.makeTerm(t), makeRecTuple(i-1, t)); 
    }

    public void testClosure2f() {
        interpTest("closure_test_2f", makeRecTuple(10, "5"), makeRecTuple(10, "3"));
    }

    public void testClosure4() {
        interpTest("closure_test_4", itp.makeTerm("1"), itp.makeTerm("3"));
    }

    public void testClosure5a() {
        interpTest("closure_test_5a", itp.makeList("[1,2,3]"), itp.makeList("[1,1,1]"));
    }

    public void testClosure5b() {
        interpTest("closure_test_5b", itp.makeList("[1,2,3]"), itp.makeList("[1,1,1]"));
    }

    public void testClosure6() {
        interpTest("closure_test_6", itp.makeTuple("[]"), itp.makeTerm("1"));
    }

    public void testClosure7() {
        interpTest("closure_test_7", 
                   itp.makeList("[5, 5, 5, 5, 5, 5, 5, 5, 5, 5]"), 
                   itp.makeList("[4, 6, 4, 6, 4, 6, 4, 6, 4, 6]"));
    }

    public void testClosure8() {
        interpTest("closure_test_8", 
                   itp.makeList("[5, 5, 5, 5, 5, 5, 5, 5, 5, 5]"), 
                   itp.makeList("[4, 6, 4, 6, 4, 6, 4, 6, 4, 6]"));
    }

    public void testClosure9() {
        interpTest("closure_test_9", 
                   itp.makeList("[5, 5, 5, 5, 5, 5, 5, 5, 5, 5]"), 
                   itp.makeList("[4, 6, 4, 6, 4, 6, 4, 6, 4, 6]"));
    }

}

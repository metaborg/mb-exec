/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

public class ArithmeticTest extends LanguageTest {

    public void testAddInt1() {
        interpTest("add_int_1", itp.makeTuple("[1,2]"), itp.makeTerm("3"));
    }

    public void testAddInt2() {
        interpTest("add_int_2", itp.makeTuple("[]"), itp.makeTerm("3"));
    }

    public void testGtInt1() {
        interpTest("gt_int_1", itp.makeTuple("[]"), itp.makeTuple("[2, 1]"));
    }

    public void testGtInt2() {
        interpTestFail("gt_int_2", itp.makeTuple("[]"));
    }

    public void testIncInt() {
        interpTest("inc_int", itp.makeTerm("1"), itp.makeTerm("2"));
    }

    public void testIncIntList2() {
        interpTest("inc_int_list_2", itp.makeList("[1,2,3]"), itp.makeList("[2,3,4]"));
    }

    public void testIntToString() {
        interpTest("int-to-string", itp.makeTerm("14"), itp.makeTerm("\"14\""));
    }

    public void testMulInt() {
        interpTest("mul_int", itp.makeTuple("[2,3]"), itp.makeTerm("6"));
    }

    public void testSumOfIntList1() {
        interpTest("sum_of_int_list", itp.makeList("[1,2,3]"), itp.makeTerm("6"));
    }

    public void testSumOfIntList2() {
        interpTest("sum_of_int_list", itp.makeList("[1,1,1,1,1,1,1,1,1,1]"), itp.makeTerm("10"));
    }

}

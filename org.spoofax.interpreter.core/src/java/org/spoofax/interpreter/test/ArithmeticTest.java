/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

import java.io.IOException;

import org.spoofax.interpreter.core.InterpreterException;

public class ArithmeticTest extends AbstractLanguageTest {

    public void testAddInt1() throws IOException, InterpreterException {
        interpTest("add_int_1", factory.parseFromString("(1,2)"), factory.parseFromString("3"));
    }

    public void testAddInt2() throws IOException, InterpreterException {
        interpTest("add_int_2", factory.parseFromString("[]"), factory.parseFromString("3"));
    }

    public void testGtInt1() throws IOException, InterpreterException {
        interpTest("gt_int_1", factory.parseFromString("[]"), factory.parseFromString("(2,1)"));
    }

    public void testGtInt2() throws IOException, InterpreterException {
        interpTestFail("gt_int_2", factory.parseFromString("[]"));
    }

    public void testIncInt() throws IOException, InterpreterException {
        interpTest("inc_int", factory.parseFromString("1"), factory.parseFromString("2"));
    }

    public void testIncIntList2() throws IOException, InterpreterException {
        interpTest("inc_int_list_2", factory.parseFromString("[1,2,3]"), factory.parseFromString("[2,3,4]"));
    }

    public void testIntToString() throws IOException, InterpreterException {
        interpTest("int-to-string", factory.parseFromString("14"), factory.parseFromString("\"14\""));
    }

    public void testMulInt() throws IOException, InterpreterException {
        interpTest("mul_int", factory.parseFromString("(2,3)"), factory.parseFromString("6"));
    }

    public void testSumOfIntList1() throws IOException, InterpreterException {
        interpTest("sum_of_int_list", factory.parseFromString("[1,2,3]"), factory.parseFromString("6"));
    }

    public void testSumOfIntList2() throws IOException, InterpreterException {
        interpTest("sum_of_int_list", factory.parseFromString("[1,1,1,1,1,1,1,1,1,1]"), factory.parseFromString("10"));
    }

}

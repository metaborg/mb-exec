/*
 * Created on 24.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test.language;

import java.io.IOException;

import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.test.AbstractLanguageTest;


public class TestTermConstruction extends AbstractLanguageTest {

    public void testTermConstr1() throws IOException, InterpreterException {
        interpTest("term_constr_1", "()", "Pair(1,2)");
    }

    public void testTermConstr2() throws IOException, InterpreterException {
        interpTest("term_constr_2", "()", "(1,2,3)");
    }

    public void testTermConstr3() throws IOException, InterpreterException {
        interpTest("term_constr_3", "()", "[1,2,3]");
    }

    public void testTermConstr4() throws IOException, InterpreterException {
        interpTest("term_constr_4", "()", "1");
    }

    public void testTermConstr5() throws IOException, InterpreterException {
        interpTest("term_constr_5", "()", "4.5");
    }

    public void testTermConstr6() throws IOException, InterpreterException {
        interpTest("term_constr_6", "(\"Pair\",[3,4]))", "Pair(3,4)");
    }

    public void testTermConstr6b() throws IOException, InterpreterException {
        interpTest("term_constr_6", 
                   "(1,[1,2,3,4])",
                   "1");
    }

    public void testTermConstr6c() throws IOException, InterpreterException {
        interpTest("term_constr_6", "(\"foo\",[])", "foo");
    }

    public void testTermConstr6d() throws IOException, InterpreterException {
        interpTest("term_constr_6", "(\"\\\"foo\\\"\",[])", "\"foo\"");
    }
}

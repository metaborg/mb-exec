/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test.language;

import java.io.IOException;

import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.test.AbstractLanguageTest;

public class TestTermDeconstruction extends AbstractLanguageTest {

    public void testTermDeconstr1a() throws IOException, InterpreterException {
        interpTest("term_deconstr_1", "1", "1");
    }

    public void testTermDeconstr1b() throws IOException, InterpreterException {
        interpTest("term_deconstr_3", "1", "[]");
    }

    public void testTermDeconstr2a() throws IOException, InterpreterException {
        interpTest("term_deconstr_1", 
                   "Plus(1,2)", "\"Plus\"");
    }

    public void testTermDeconstr2b() throws IOException, InterpreterException {
        interpTest("term_deconstr_3", "Plus(1,2)", "[1,2]");
    }

    public void testTermDeconstr3() throws IOException, InterpreterException {
        interpTest("term_deconstr_2", "Plus(1,2)", "[2]");
    }

    public void testTermDeconstr4() throws IOException, InterpreterException {
        interpTest("term_deconstr_4", "Plus(1,2)", "[1,2]");
    }

    public void testTermDeconstr5() throws IOException, InterpreterException {
        interpTest("term_deconstr_5", "Pair(1,2)", "Pair(1,2)");
    }

    public void testTermDeconstr6a() throws IOException, InterpreterException {
        interpTest("term_deconstr_1", "4.5", "4.5");
    }

    public void testTermDeconstr6b() throws IOException, InterpreterException {
        interpTest("term_deconstr_3", "4.5", "[]");
    }

    public void testTermDeconstr7a() throws IOException, InterpreterException {
        interpTest("term_deconstr_1", "\"foo\"", "\"\\\"foo\\\"\"");
    }

    public void testTermDeconstr7b() throws IOException, InterpreterException {
        interpTest("term_deconstr_3", "\"foo\"", "[]");
    }

    public void testTermDeconstr8a() throws IOException, InterpreterException {
        interpTest("term_deconstr_1", "[1,2,3]", "[]");
    }

    public void testTermDeconstr8b() throws IOException, InterpreterException {
        interpTest("term_deconstr_3", "[1,2,3]", "[1,2,3]");
    }

    public void testTermDeconstr9a() throws IOException, InterpreterException {
        interpTest("term_deconstr_1", "(1,2,3)", "\"\"");
    }

    public void testTermDeconstr9b() throws IOException, InterpreterException {
        interpTest("term_deconstr_3", "[1,2,3]", "[1,2,3]");
    }

    public void testTermSize() throws IOException, InterpreterException {
        interpTest("term-size", "2", "1");
    }

    public void testTermArg1() throws IOException, InterpreterException {
        interpTest("id_term-arg_1", "()", "3");
    }

}

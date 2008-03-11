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

public class TestBuild extends AbstractLanguageTest {

    public void testBuildInt() throws IOException, InterpreterException {
        interpTest("build_int", "()", "5");
    }

    public void testBuildReal() throws IOException, InterpreterException {
        interpTest("build_real", "()", "5.0");
    }

    public void testBuildString() throws IOException, InterpreterException {
        interpTest("build_string", "()", "\"a\"");
    }

    public void testBuildTuple() throws IOException, InterpreterException {
        interpTest("build_tuple", "()", "(2,3)");
    }

    public void testBuildList1() throws IOException, InterpreterException {
        interpTest("build_list_1", "()", "[]");
    }

    public void testBuildList2() throws IOException, InterpreterException {
        interpTest("build_list_2", "()", "[1,2,3]");
    }

    public void testBuildList3() throws IOException, InterpreterException {
        interpTest("build_list_3", "()", "[1,2,3]");
    }

    public void testBuildList4() throws IOException, InterpreterException {
        try {
            interpTestFail("build_list_4", "1");
        } catch(InterpreterException e) {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }
    
    public void testBuildList4b() throws IOException, InterpreterException {
        interpTest("build_list_4", "[3]", "[1,2,3]");
    }
}
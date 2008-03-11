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

public class TestDefinition extends AbstractLanguageTest {

    public void testRDef1() throws IOException, InterpreterException {
        interpTest("foo_rdef_1", "1", "2");
    }

    public void testRDef2() throws IOException, InterpreterException {
        interpTest("foo_rdef_2", "()", "(2,1)");
    }

    public void testRDef3() throws IOException, InterpreterException {
        interpTest("foo_rdef_3", "()", "2");
    }

    public void testRDef4() throws IOException, InterpreterException {
        interpTestFail("foo_rdef_4", "()");
    }

    public void testSDef1() throws IOException, InterpreterException {
        interpTest("foo_sdef_1", "()", "3");
    }

    public void testSDef2() throws IOException, InterpreterException {
        interpTest("foo_sdef_2", "()", "3");
    }

    public void testSDef3() throws IOException, InterpreterException {
        interpTest("foo_sdef_3", "()", "3");
    }

}

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

public class TestTraversals extends AbstractLanguageTest {

    public void testAll1() throws IOException, InterpreterException {
        interpTest("all_1", "(1,2,3)", "(1,1,1)");
    }

    public void testAll2() throws IOException, InterpreterException {
        interpTest("all_2", "(1,1,1)", "(1,1,1)");
    }

    public void testAll2b() throws IOException, InterpreterException {
        interpTestFail("all_2", "(1,1,2)");
    }

    public void testAll3() throws IOException, InterpreterException {
        interpTest("all_3", "(1,1,1)", "(2,2,2)");
    }

    public void testAll3b() throws IOException, InterpreterException {
        interpTestFail("all_3", "(1,2,3)");
    }

    public void testAll4() throws IOException, InterpreterException {
        interpTest("all_4", "()", "()");
    }

    public void testAll4b() throws IOException, InterpreterException {
        interpTestFail("all_4", "(1,2,3)");
    }

    public void testSome1() throws IOException, InterpreterException {
        interpTest("some_1", "(1,2,3)", "(1,1,1)");
    }

    public void testSome2() throws IOException, InterpreterException {
        interpTest("some_2", "(1,2,3)", "(1,2,3)");
    }
    
    public void testSome2b() throws IOException, InterpreterException {
        interpTest("some_2", "(2,1,3)", "(2,1,3)");
    }
    
    public void testSome3() throws IOException, InterpreterException {
        interpTest("some_3", "(1,2,3)", "(2,2,3)");
    }

    public void testSome3b() throws IOException, InterpreterException {
        interpTestFail("some_3", "(2,2,3)");
    }
    
    public void testOne1() throws IOException, InterpreterException {
        interpTest("one_1", "(1,2,3)", "(1,2,3)");
    }
    
    public void testOne1b() throws IOException, InterpreterException {
        interpTest("one_1", "(2,2,3)", "(1,2,3)");
    }

    public void testOne2() throws IOException, InterpreterException {
        interpTest("one_2", "(2,2,1)", "(2,2,1)");
    }

    public void testOne2b() throws IOException, InterpreterException {
        interpTestFail("one_2", "(2,2,2)");
    }

    public void testOne3() throws IOException, InterpreterException {
        interpTest("one_3", "(1,1,1)", "(2,1,1)");
    }

    public void testOne3b() throws IOException, InterpreterException {
        interpTestFail("one_3", "(2,2,2)");
    }

}
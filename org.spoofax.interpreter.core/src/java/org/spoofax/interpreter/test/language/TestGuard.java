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

public class TestGuard extends AbstractLanguageTest {

    public void testGuarded1() throws IOException, InterpreterException {
        interpTest("guarded_1", "()", "1");
    }

    public void testGuarded2() throws IOException, InterpreterException {
        interpTest("guarded_2", "()", "2");
    }

    public void testGuarded3() throws IOException, InterpreterException {
        interpTest("guarded_3", "()", "3");
    }

    public void testGuarded4() throws IOException, InterpreterException {
        interpTest("guarded_4", "5", "5");
    }

    public void testGuarded5() throws IOException, InterpreterException {
        interpTest("guarded_5", "()", "1");
    }

    public void testGuarded6() throws IOException, InterpreterException {
        interpTest("guarded_6", "()", "1");
    }

    public void testGuarded7() throws IOException, InterpreterException {
        interpTest("guarded_7", "()", "2");
    }

    public void testLeftChoiceGuard() throws IOException, InterpreterException {
        interpTest("guarded_modifies_current_term", "()", "3");
    }

}

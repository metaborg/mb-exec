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

public class TestOverloading extends AbstractLanguageTest {

    public void testOverloading1() throws IOException, InterpreterException {
        interpTest("overloading_1", "()", "1");
    }

    public void testOverloading2() throws IOException, InterpreterException {
        interpTest("overloading_2", "()", "2");
    }

    public void testOverloading3() throws IOException, InterpreterException {
        interpTest("overloading_3", "()", "1");
    }

    public void testOverloading4() throws IOException, InterpreterException {
        interpTest("overloading_4", "()", "2");
    }

}

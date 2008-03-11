/*
 * Created on 02.mar.2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test.language;

import java.io.IOException;

import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.test.AbstractLanguageTest;

public class TestHigherOrderParameters extends AbstractLanguageTest {

    public void testHigherOrderParam1() throws IOException, InterpreterException {
        interpTest("higher_order_param_1", "()", "5");
    }

    public void testHigherOrderParam2() throws IOException, InterpreterException {
        interpTest("higher_order_param_2", "()", "5");
    }
    
    public void testHigherOrderParam3() throws IOException, InterpreterException {
        interpTest("higher_order_param_3", "()", "5");
    }
    
    public void testHigherOrderParam4() throws IOException, InterpreterException {
        interpTest("higher_order_param_4", "()", "5");
    }
}

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

public class QuickTest extends AbstractLanguageTest {

    @Override
    protected void setUp() throws Exception {
        super.setUp("tests/data/");
    }
    
    public void testQuick() throws IOException, InterpreterException {
        interpTest("quick", factory.parseFromString("()"), factory.parseFromString("()"));
    }
}

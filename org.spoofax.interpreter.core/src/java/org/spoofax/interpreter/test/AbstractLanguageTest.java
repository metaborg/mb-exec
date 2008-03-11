/*
 * Created on 05.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter.test;

public abstract class AbstractLanguageTest extends AbstractInterpreterTest {

    @Override
    protected void setUp() throws Exception {
        super.setUp("tests/data/lang/");
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(AbstractLanguageTest.class);
    }
}

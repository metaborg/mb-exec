/*
 * Created on 11.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

import java.io.IOException;

import junit.framework.TestCase;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.core.Interpreter;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public abstract class AbstractInterpreterTest extends TestCase {

    protected Interpreter itp;
    protected ITermFactory factory;
    protected String basePath;
    private boolean unitTestDebug = false;
    
    protected void setUp(String path) throws Exception {
        super.setUp();
        basePath = path;
        itp = new Interpreter();
        factory = itp.getFactory();
        DebugUtil.setDebug(false);
        DebugUtil.setTracing(false);
        unitTestDebug = true; 
    }
    
    @Override
    protected void tearDown() throws Exception {
        itp.shutdown();
        itp = null;
        factory = null;
        super.tearDown();
    }

    public void interpTestFail(String test, IStrategoTerm input) throws IOException, InterpreterException {
        assertFalse(runInterp(test, input));
    }

    public void interpTestFail(String test, String input) throws IOException, InterpreterException {
        IStrategoTerm t = itp.getFactory().parseFromString(input);
        interpTestFail(test, t);
    }

    public void interpTest(String test, String input, String output) throws IOException, InterpreterException {
        IStrategoTerm i = factory.parseFromString(input);
        IStrategoTerm o = factory.parseFromString(output);
        interpTest(test, i, o);
    }

    public void interpTest(String test, IStrategoTerm input, IStrategoTerm output) throws IOException, InterpreterException {
        if(unitTestDebug) {
            System.out.println("Input : " + input);
        }
        assertTrue("main strategy failed", runInterp(test, input));
        IStrategoTerm x = output;
        IStrategoTerm y = itp.current();
        if(unitTestDebug) {
            System.out.println("Want  : " + x + " / " + x.getTermType() + " / " + x.getClass()
                               + " / " + x.getSubtermCount());
            System.out.println("Got   : " + y + " / " + y.getTermType() + " / " + y.getClass()
                               + " / " + y.getSubtermCount());
        }
        boolean succeeded = itp.current().match(output);
        if(unitTestDebug) {
            System.out.println(succeeded);
        }
        assertTrue("actual output differs from expected output", succeeded);
    }

    private boolean runInterp(String test, IStrategoTerm input) throws IOException, InterpreterException {
        itp.load(basePath + "/" + test + ".ctree");
        itp.setCurrent(input);
        // System.out.println("Input : " + input);
        return itp.invoke("main_0_0");
    }

}

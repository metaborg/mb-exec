/*
 * Created on 11.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

import java.io.IOException;

import junit.framework.TestCase;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public abstract class AbstractInterpreterTest extends TestCase {

    protected Interpreter itp;
    protected ITermFactory factory;
    protected String basePath;
    
    protected void setUp(String path) throws Exception {
        super.setUp();
        basePath = path;
        DebugUtil.resetSSL = false; //faster but does not cleanup
        DebugUtil.cleanupInShutdown = false; //faster but does not cleanup
        DebugUtil.shareFactory = true; // in unit test mode all can share the same factory
        itp = new Interpreter();
        factory = itp.getFactory();
        DebugUtil.setDebug(false);
    }
    
    @Override
    protected void tearDown() throws Exception {
        itp.shutdown();
        itp = null;
        super.tearDown();
    }

    public void interpTestFail(String test, IStrategoTerm input) {
        assertFalse(runInterp(test, input));
    }

    public void interpTestFail(String test, String input) {
        IStrategoTerm t = itp.getFactory().parseFromString(input);
        interpTestFail(test, t);
    }

    public void interpTest(String test, String input, String output) {
        IStrategoTerm i = factory.parseFromString(input);
        IStrategoTerm o = factory.parseFromString(output);
        interpTest(test, i, o);
    }

    public void interpTest(String test, IStrategoTerm input, IStrategoTerm output) {
        if(DebugUtil.debugging) {
            System.out.println("Input : " + input);
        }
        assertTrue(runInterp(test, input));
        IStrategoTerm x = output;
        IStrategoTerm y = itp.current();
        if(DebugUtil.debugging) {
            System.out.println("Want  : " + x + " / " + x.getTermType() + " / " + x.getClass()
                               + " / " + x.getSubtermCount());
            System.out.println("Got   : " + y + " / " + y.getTermType() + " / " + y.getClass()
                               + " / " + y.getSubtermCount());
        }
        boolean succeeded = itp.current().match(output);
        if(DebugUtil.debugging) {
            System.out.println(succeeded);
        }
        assertTrue(succeeded);
    }

    private boolean runInterp(String test, IStrategoTerm input) {
        try {
        itp.load(basePath + "/" + test + ".rtree");
        itp.setCurrent(input);
        // System.out.println("Input : " + input);
        return itp.invoke("main_0_0");
    
        } catch(InterpreterException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    
    }

}

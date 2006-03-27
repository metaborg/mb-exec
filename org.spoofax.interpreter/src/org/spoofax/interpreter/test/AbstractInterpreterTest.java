/*
 * Created on 11.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

import java.io.IOException;
import java.util.List;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.Interpreter;

import aterm.ATerm;
import aterm.pure.ATermImpl;
import junit.framework.TestCase;

public abstract class AbstractInterpreterTest extends TestCase {

    protected Interpreter itp;
    protected String basePath;
    
    protected void setUp(String path) throws Exception {
        super.setUp();
        basePath = path;
        System.out.println("!!!!!!!!!!!!!!!!!!!!!");
        itp = new Interpreter();
    }
    
    @Override
    protected void tearDown() throws Exception {
        itp = null;
        super.tearDown();
    }

    public void interpTestFail(String test, ATerm input) {
        assertFalse(runInterp(test, input));
    }

    public void interpTestFail(String test, String input) {
        ATerm t = itp.makeTerm(input);
        interpTestFail(test, t);
    }

    public void interpTest(String test, String input, String output) {
        ATerm i = itp.makeTerm(input);
        ATerm o = itp.makeTerm(output);
        interpTest(test, i, o);
    }

    public void interpTest(String test, ATerm input, ATerm output) {
        System.out.println("Input : " + input);
        assertTrue(runInterp(test, input));
        ATermImpl x = (ATermImpl) output;
        ATermImpl y = (ATermImpl) itp.current();
        assertTrue(x.getFactory() == y.getFactory());
        System.out.println("Want  : " + x + " / " + x.getType() + " / " + x.getClass()
                + " / " + x.getChildCount());
        System.out.println("Got   : " + y + " / " + y.getType() + " / " + y.getClass()
                           + " / " + y.getChildCount());
        final List succeeded = itp.current().match(output);
        System.out.println(succeeded != null);
        assertTrue(succeeded != null);
    }

    private boolean runInterp(String test, ATerm input) {
        try {
        itp.load(basePath + "/" + test + ".rtree");
        System.out.println(itp.prettyPrint());
        itp.setCurrent(input);
        // System.out.println("Input : " + input);
        return itp.invoke("main_0_0");
    
        } catch(FatalError e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    
    }

}

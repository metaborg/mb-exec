/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.demo;

import java.io.IOException;

import junit.framework.TestCase;

import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.adapters.ecj.ECJFactory;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class TestECJAdapter extends TestCase {

    public void testTest1() throws IOException, InterpreterException {
        interpTest("test1", "()", "()");
    }

    public void testTest2() throws IOException, InterpreterException {
        interpTest("test2", "\"src/org/spoofax/interpreter/demo/ECJ.java\"", "()");
    }

    private void interpTest(String string, String in, String out) throws IOException, InterpreterException {
        ECJFactory f = new ECJFactory();
        Interpreter itp = new Interpreter(f);
        itp.load("str/" + string + ".rtree");
        IStrategoTerm inTerm = f.parseFromString(in);
        IStrategoTerm outTerm = f.parseFromString(out);
        itp.setCurrent(inTerm);
        assertTrue(itp.invoke("main_0_0"));
        assertTrue(inTerm.match(outTerm));
    }
}

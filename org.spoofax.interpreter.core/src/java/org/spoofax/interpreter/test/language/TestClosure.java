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
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.interpreter.test.AbstractLanguageTest;

public class TestClosure extends AbstractLanguageTest {

    public void testClosure1() throws IOException, InterpreterException {
        interpTest("closure_test_1", 
                   factory.parseFromString("[]"), 
                   factory.parseFromString("1"));
    }

    public void testClosure10() throws IOException, InterpreterException {
        interpTest("closure_test_10", 
                   factory.parseFromString("[5,5,5,5,5,5,5,5,5,5]"), 
                   factory.parseFromString("[4,6,4,6,4,6,4,6,4,6]"));
    }

    public void testClosure11() throws IOException, InterpreterException {
           interpTest("closure_test_11", 
                      factory.parseFromString("[5,5,5,5,5,5,5,5,5,5]"), 
                      factory.parseFromString("[4,6,4,6,4,6,4,6,4,6]"));
       }

    public void testClosure12() throws IOException, InterpreterException {
           interpTest("closure_test_12", 
                      factory.parseFromString("[5,5,5,5,5,5,5,5,5,5]"), 
                      factory.parseFromString("[4,6,4,6,4,6,4,6,4,6]"));
       }

    public void testClosure13() throws IOException, InterpreterException {
           interpTest("closure_test_13", 
                      factory.parseFromString("1"), 
                      factory.parseFromString("4"));
       }

    public void testClosure14() throws IOException, InterpreterException {
        interpTest("closure_test_14", 
                   factory.parseFromString("1"), 
                   factory.parseFromString("6"));
    }

    public void testClosure15() throws IOException, InterpreterException {
        interpTest("closure_test_15", 
                   factory.parseFromString("[[1,2,3],[4,5,6]]"), 
                   factory.parseFromString("[[2,3,4],[5,6,7]]"));
    }

    public void testClosure2() throws IOException, InterpreterException {
        interpTest("closure_test_2", 
                   factory.parseFromString("[]"), 
                   factory.parseFromString("1"));
    }

    public void testClosure2b() throws IOException, InterpreterException {
        interpTest("closure_test_2b", 
                   factory.parseFromString("[5,5,5,5,5,5,5,5,5,5]"), 
                   factory.parseFromString("[3,3,3,3,3,3,3,3,3,3]"));
    }

    public void testClosure2c() throws IOException, InterpreterException {
        interpTest("closure_test_2c", 
                   factory.parseFromString("[5,5,5,5,5,5,5,5,5,5]"), 
                   factory.parseFromString("[3,3,3,3,3,3,3,3,3,3]"));
    }

    private IStrategoTerm makeRecTuple(int i, String t) {
        ITermFactory fac = itp.getFactory();
        if(i == 0)
            return fac.makeTuple();
        return fac.makeTuple(fac.parseFromString(t), makeRecTuple(i-1, t)); 
    }

    public void testClosure2f() throws IOException, InterpreterException {
        interpTest("closure_test_2f", makeRecTuple(10, "5"), makeRecTuple(10, "3"));
    }

    public void testClosure4() throws IOException, InterpreterException {
        interpTest("closure_test_4", factory.parseFromString("1"), factory.parseFromString("3"));
    }

    public void testClosure5a() throws IOException, InterpreterException {
        interpTest("closure_test_5a",factory.parseFromString("[1,2,3]"), factory.parseFromString("[1,1,1]"));
    }

    public void testClosure5b() throws IOException, InterpreterException {
        interpTest("closure_test_5b", factory.parseFromString("[1,2,3]"), factory.parseFromString("[1,1,1]"));
    }

    public void testClosure6() throws IOException, InterpreterException {
        interpTest("closure_test_6", factory.parseFromString("[]"), factory.parseFromString("1"));
    }

    public void testClosure7() throws IOException, InterpreterException {
        interpTest("closure_test_7", 
                   factory.parseFromString("[5,5,5,5,5,5,5,5,5,5]"), 
                   factory.parseFromString("[4,6,4,6,4,6,4,6,4,6]"));
    }

    public void testClosure8() throws IOException, InterpreterException {
        interpTest("closure_test_8", 
                   factory.parseFromString("[5,5,5,5,5,5,5,5,5,5]"), 
                   factory.parseFromString("[4,6,4,6,4,6,4,6,4,6]"));
    }

    public void testClosure9() throws IOException, InterpreterException {
        interpTest("closure_test_9", 
                   factory.parseFromString("[5,5,5,5,5,5,5,5,5,5]"), 
                   factory.parseFromString("[4,6,4,6,4,6,4,6,4,6]"));
    }

}

/*
 * Created on 18. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

import java.io.IOException;

import org.spoofax.interpreter.core.Interpreter;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SpeedTest {

    private final static String[] coreFiles = { "char-test.ctree", "collect-test.ctree",
            "dynamic-rules-highlevel-test.ctree", "dynamic-rules-lowlevel-test.ctree",
            "env-traversal-test.ctree", "integers-test.ctree", "int-list-test.ctree",
            "iteration-test.ctree", "list-basic-test.ctree", "list-filter-test.ctree",
            "list-index-test.ctree", "list-misc-test.ctree", "list-set-test.ctree",
            "list-sort-test.ctree", "list-zip-test.ctree", "old-parse-options-test.ctree",
            "parenthesize-test.ctree", "parse-options-test.ctree", "reals-test.ctree",
            "rename-test.ctree", "scoped-finite-map-test.ctree", "sets-test.ctree",
            "simple-traversal-test.ctree", "strcmp-test.ctree", "string-misc-test.ctree",
            "string-test.ctree", "substitution-test.ctree", // "system-io-file-test.ctree",
            "tables-test.ctree", "term-common-test.ctree", "term-zip-test.ctree",
            "tuple-test.ctree", "unification-test.ctree" };

    public static void main(String[] args) throws IOException, InterpreterException {
        Interpreter itp = new Interpreter();
        IStrategoTerm init = itp.getFactory().makeList();

        final int sz = 3;
        long[] x = new long[sz];
        for(int i = 0; i < sz; i++) {
            x[i] = runTest(itp, init);
        }
        
        for(int i = 0; i < sz; i++) {
            System.out.println("Elapsed (" + i + ") : " + x[i]);
        }
    }
    
    static long runTest(Interpreter itp, IStrategoTerm init) throws IOException, InterpreterException {
        long start = System.nanoTime();
        for(int i = 0; i < coreFiles.length; i++) {
            itp.load("tests/data/library/" + coreFiles[i]);
            itp.setCurrent(init);
            itp.invoke("main_0_0");
        }
        return (System.nanoTime() - start)/1000/1000;
    }
}

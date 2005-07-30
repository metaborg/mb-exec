/*
 * Created on 05.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interp.test;

import java.io.IOException;

import junit.framework.TestCase;

import org.spoofax.interp.FatalError;
import org.spoofax.interp.Interpreter;

public class LibraryTest extends TestCase {

    Interpreter itp;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        itp = new Interpreter();
    }
    
    public void testTuple() {  interpTest("tuple-test");   }
    public void testTemplate() {  interpTest("template-test");   }
    public void testPOSIXError() {  interpTest("posix-error-test");   }
    public void testReals() {  interpTest("reals-test");   }
    public void testPlaceholder() {  interpTest("placeholder-test");   }
    public void testSubstitution() {  interpTest("substitution-test");   }
    public void testListFilter() {  interpTest("lister-filter-test");   }
    public void testUnification() { interpTest("unification-test"); } 
    public void testListZip() { interpTest("list-zip-test"); }
    public void testIO() { interpTest("io-test"); }
    public void testDir() { interpTest("dir-test"); }
    public void testEnvTraversal() { interpTest("env-traversal-test"); }
    public void testRename() { interpTest("rename-test"); }
    public void testShare() { interpTest("share-test"); }
    public void testIteration() { interpTest("iteration-test"); }
    public void testIntList() { interpTest("int-list-test"); }
    public void testApply() { interpTest("apply-test"); }
    public void testSets() { interpTest("sets-test"); }
    public void testPOSIXFile() { interpTest("posix-file-test"); }
    public void testListIndex() { interpTest("list-index-test"); }
    public void testPOSIXProcess() { interpTest("posix-process-test"); }
    public void testTermZip() { interpTest("term-zip-test"); }
    public void testScopedFiniteMap() { interpTest("scoped-finite-map-test"); }
    public void testStrcmp() { interpTest("strcmp-test"); }
    public void testSimpleTraversal() { interpTest("simple-traversal-test"); }
    public void testCollect() { interpTest("collect-test"); }
    public void testListMisc() { interpTest("list-misc-test"); }
    public void testListSet() { interpTest("list-set-test"); }
    public void testStringMisc() { interpTest("string-misc-test"); }
    public void testIntegers() { interpTest("integers-test"); }
    public void testListBasic() { interpTest("list-basic-test"); }
    public void testTime() { interpTest("time-test"); }
    public void testParenthesize() { interpTest("parenthesize-test"); }
    public void testParseOptions() { interpTest("parse-options-test"); }
    public void testString() { interpTest("string-test"); }
    public void testFile() { interpTest("file-test"); }
    public void testTables() { interpTest("tables-test"); }
    public void testDynamicRulesLowlevel() { interpTest("dynamic-rules-lowlevel-test"); }
    public void testDynamicRulesHighlevel() { interpTest("dynamic-rules-highlevel-test"); }


    public void interpTest(String test) {
        assertTrue(runInterp(test));
    }

    private boolean runInterp(String test) {
        itp.reset();

        try {
        itp.load("/home/karltk/source/oss/spoofax/spoofax/core/tests/data/"
                + test + ".rtree");
        return itp.eval(itp.makeTerm("CallT(SVar(\"main_0_0\"), [], [])"));
        } catch(FatalError e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
}

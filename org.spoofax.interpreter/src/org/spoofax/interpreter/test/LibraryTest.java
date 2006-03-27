/*
 * Created on 05.jul.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter.test;


public class LibraryTest extends InterpreterTest {

    @Override
    protected void setUp() throws Exception {
        super.setUp("tests/data/library");
    }

    public void testTuple() {  interpTest("tuple-test", "[2,0]"); }
    public void testTemplate() {  interpTest("template-test", "[1,0]"); }
    public void testReals() {  interpTest("reals-test", "[8,0]"); }
    public void testListSet() { interpTest("list-set-test", "[25,0]"); }
    public void testListZip() { interpTest("list-zip-test", "[3,0]"); }
    public void testIntList() { interpTest("int-list-test", "[15,0]"); }
    public void testListMisc() { interpTest("list-misc-test", "[23,0]"); }
    public void testListIndex() { interpTest("list-index-test", "[17,0]"); }
    public void testListFilter() {  interpTest("list-filter-test", "[2,0]");   }
    public void testIntegers() { interpTest("integers-test", "[79,0]"); }
    public void testIteration() { interpTest("iteration-test", "[25,0]"); }
    public void testUnification() { interpTest("unification-test", "[2,0]"); }
    public void testStrcmp() { interpTest("strcmp-test", "[16,0]"); }
    public void testTermZip() { interpTest("term-zip-test", "[8,0]"); }
    public void testEnvTraversal() { interpTest("env-traversal-test", "[2,0]"); }
    public void testSets() { interpTest("sets-test", "[11,0]"); }
    public void testTables() { interpTest("tables-test", "[50,0]"); }
    public void testSubstitution() {  interpTest("substitution-test", "[1,0]");   }
    public void testListBasic() { interpTest("list-basic-test" ,"[17,0]"); }
    public void testStringMisc() { interpTest("string-misc-test", "[14,0]"); }

/*
 *  Relies on ANSI C and/or POSIX semantics
    public void testPOSIXError() {  interpTest("posix-error-test");   }
    public void testIO() { interpTest("io-test"); }
    public void testDir() { interpTest("dir-test"); }
    public void testPOSIXFile() { interpTest("posix-file-test"); }
    public void testPOSIXProcess() { interpTest("posix-process-test"); }
    public void testTime() { interpTest("time-test"); }
    
 *  Known not to work.
    public void testPlaceholder() {  interpTest("placeholder-test");   }
    public void testShare() { interpTest("share-test"); }
    public void testDynamicRulesLowlevel() { interpTest("dynamic-rules-lowlevel-test"); }
    public void testDynamicRulesHighlevel() { interpTest("dynamic-rules-highlevel-test"); }

*/

    public void testFile() { interpTest("file-test", "[97,0]"); }
    public void testString() { interpTest("string-test", "[55,0]"); }
    public void testRename() { interpTest("rename-test", "[2,0]"); }
    public void testApply() { interpTest("apply-test", "[4,0]"); }
    public void testScopedFiniteMap() { interpTest("scoped-finite-map-test", "[5,0]"); }
    public void testSimpleTraversal() { interpTest("simple-traversal-test", "[6,0]"); }
    public void testCollect() { interpTest("collect-test", "[11,0]"); }
    public void testParenthesize() { interpTest("parenthesize-test", "[4,0]"); }
    public void testParseOptions() { interpTest("parse-options-test" ,"[2,0]"); }

    public void interpTest(String test, String result) {
        super.interpTest(test, itp.makeTuple("[]"), itp.makeTuple(result));
    }
}


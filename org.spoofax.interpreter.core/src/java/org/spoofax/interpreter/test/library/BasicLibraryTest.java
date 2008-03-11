/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test.library;

import java.io.IOException;

import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.test.AbstractLanguageTest;

public class BasicLibraryTest extends AbstractLanguageTest {

    public void testCollectOm1() throws IOException, InterpreterException {
        interpTest("collect-om_1", 
                   "[1,(2,3),3]",
                   "[1,2,3]");
    }

    public void testConc() throws IOException, InterpreterException {
        interpTest("conc", 
                   "([1,2,3],[3,4,5])",
                   "[1,2,3,3,4,5]");
    }

    public void testConcat() throws IOException, InterpreterException {
        interpTest("concat", 
                   "[[1,2,3],[3,4,5]]",
                   "[1,2,3,3,4,5]");
    }

    public void testDynruleCounter1() throws IOException, InterpreterException {
        interpTest("dynrule_counter_1", "()", "2");
    }

    public void testExplodeString() throws IOException, InterpreterException {
        interpTest("explode-string", "\"ab\"", "[97,98]");
    }

    public void testFetch1() throws IOException, InterpreterException {
        interpTest("fetch_1", "[1,2,3]", "2");
    }

    public void testFetch2() throws IOException, InterpreterException {
        interpTestFail("fetch_2", "[1,2,3]");
    }

    public void testFetchElem1() throws IOException, InterpreterException {
        interpTest("fetch_elem_1", "[1,2,3]", "2");
    }

    public void testFstTuple() throws IOException, InterpreterException {
        interpTest("Fst_tuple", "(1,2)", "1");
    }

    public void testSndTuple() throws IOException, InterpreterException {
        interpTest("Snd_tuple", "(1,2)", "2");
    }

    public void testSwapTuple() throws IOException, InterpreterException {
        interpTest("swap_tuple", "(1,2)", "(2,1)");
    }

    public void testTopdownTry() throws IOException, InterpreterException {
        interpTest("topdown_try",
                   "(1,2,[3,4])",
                   "(1,2,[4,4])");
    }

    public void testUnion() throws IOException, InterpreterException {
        interpTest("union", 
                   "([1,2,3],[3,4,5])",
                   "[1,2,3,4,5]");
    }

    public void testTest1() throws IOException, InterpreterException {
        interpTest("test_1", "2", "2");
    }

    public void testTest2() throws IOException, InterpreterException {
        interpTestFail("test_2", "3");
    }

    public void testTest3() throws IOException, InterpreterException {
        interpTestFail("test_3", "()"); 
    }

    public void testTestMap1() throws IOException, InterpreterException {
        interpTest("map_test_1", 
                   "[1,2,3]",
                   "[2,3,4]");
    }

    public void testTestMap2() throws IOException, InterpreterException {
        interpTest("map_test_2", "[1,2,3]", "[2,3,4]");
    }

    public void testTestMap3() throws IOException, InterpreterException {
        interpTest("map_test_3", "(5,[1,2,3])", "[6,7,8]");
    }

    public void testTestMap4() throws IOException, InterpreterException {
        interpTest("map_test_4", "[[1,2,3],[2,3,4]]", "[[2,3,4],[3,4,5]]");
    }
    
    public void testStringConcat() throws IOException, InterpreterException {
        interpTest("string_concat_1", "()", "\"abc\"");
    }

    public void testIsSubstring() throws IOException, InterpreterException {
        interpTest("is_substring_1", "()", "[102,110,111,114,100]");
    }

}

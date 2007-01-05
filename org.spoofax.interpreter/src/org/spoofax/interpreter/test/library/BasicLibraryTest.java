/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test.library;

import org.spoofax.interpreter.test.AbstractLanguageTest;

public class BasicLibraryTest extends AbstractLanguageTest {

    public void testCollectOm1() {
        interpTest("collect-om_1", 
                   "[1, (2,3), 3]",
                   "[1,2,3]");
    }

    public void testConc() {
        interpTest("conc", 
                   "([1,2,3],[3,4,5])",
                   "[1,2,3,3,4,5]");
    }

    public void testConcat() {
        interpTest("concat", 
                   "[[1,2,3],[3,4,5]]",
                   "[1,2,3,3,4,5]");
    }

    public void testDynruleCounter1() {
        interpTest("dynrule_counter_1", "()", "2");
    }

    public void testExplodeString() {
        interpTest("explode-string", "\"ab\"", "[97,98]");
    }

    public void testFetch1() {
        interpTest("fetch_1", "[1,2,3]", "2");
    }

    public void testFetch2() {
        interpTestFail("fetch_2", "[1,2,3]");
    }

    public void testFetchElem1() {
        interpTest("fetch_elem_1", "[1,2,3]", "2");
    }

    public void testFstTuple() {
        interpTest("Fst_tuple", "(1,2)", "1");
    }

    public void testSndTuple() {
        interpTest("Snd_tuple", "(1,2)", "2");
    }

    public void testSwapTuple() {
        interpTest("swap_tuple", "(1,2)", "(2,1)");
    }

    public void testTopdownTry() {
        interpTest("topdown_try",
                   "(1,2,[3,4])",
                   "(1,2,[4,4])");
    }

    public void testUnion() {
        interpTest("union", 
                   "([1,2,3],[3,4,5])",
                   "[1,2,3,4,5]");
    }

    public void testTest1() {
        interpTest("test_1", "2", "2");
    }

    public void testTest2() {
        interpTestFail("test_2", "3");
    }

    public void testTest3() {
        interpTestFail("test_3", "()"); 
    }

    public void testTestMap1() {
        interpTest("map_test_1", 
                   "[1,2,3]",
                   "[2,3,4]");
    }

    public void testTestMap2() {
        interpTest("map_test_2", "[1,2,3]", "[2,3,4]");
    }

    public void testTestMap3() {
        interpTest("map_test_3", "(5, [1,2,3])", "[6,7,8]");
    }

    public void testTestMap4() {
        interpTest("map_test_4", "[[1,2,3],[2,3,4]]", "[[2,3,4],[3,4,5]]");
    }
    
    public void testStringConcat() {
        interpTest("string_concat_1", "()", "[102,110,111,114,100]");
    }
}

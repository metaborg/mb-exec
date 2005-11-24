/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

public class BasicLibraryTest extends LanguageTest {

    public void testCollectOm1() {
        interpTest("collect-om_1", itp.makeList(itp.makeTerm("1"), itp
                .makeTuple("[2,3]"), itp.makeTerm("3")), itp
                .makeList("[1,2,3]"));
    }

    public void testConc() {
        interpTest("conc", itp.makeTuple(itp.makeList("[1,2,3]"), itp
                .makeList("[3,4,5]")), itp.makeList("[1,2,3,3,4,5]"));
    }

    public void testConcat() {
        interpTest("concat", itp.makeList(itp.makeList("[1,2,3]"), itp
                .makeList("[3,4,5]")), itp.makeList("[1,2,3,3,4,5]"));
    }

    public void testDynruleCounter1() {
        interpTest("dynrule_counter_1", itp.makeTuple("[]"), itp.makeTerm("2"));
    }

    public void testExplodeString() {
        interpTest("explode-string", itp.makeTerm("\"ab\""), itp
                .makeList("[97,98]"));
    }

    public void testFetch1() {
        interpTest("fetch_1", itp.makeList("[1,2,3]"), itp.makeTerm("2"));
    }

    public void testFetch2() {
        interpTestFail("fetch_2", itp.makeList("[1,2,3]"));
    }

    public void testFetchElem1() {
        interpTest("fetch_elem_1", itp.makeList("[1,2,3]"), itp.makeTerm("2"));
    }

    public void testFstTuple() {
        interpTest("Fst_tuple", itp.makeTuple("[1,2]"), itp.makeTerm("1"));
    }

    public void testSndTuple() {
        interpTest("Snd_tuple", itp.makeTuple("[1,2]"), itp.makeTerm("2"));
    }

    public void testSwapTuple() {
        interpTest("swap_tuple", itp.makeTuple("[1,2]"), itp.makeTuple("[2,1]"));
    }

    public void testTopdownTry() {
        interpTest("topdown_try", itp.makeTuple(itp.makeTerm("1"), itp
                .makeTerm("2"), itp.makeTuple("[3,4]")), itp.makeTuple(itp
                .makeTerm("1"), itp.makeTerm("2"), itp.makeTuple("[4,4]")));
    }

    public void testUnion() {
        interpTest("union", itp.makeTuple(itp.makeList("[1,2,3]"), itp
                .makeList("[3,4,5]")), itp.makeList("[1,2,3,4,5]"));
    }

    public void testTest1() {
        interpTest("test_1", itp.makeTerm("2"), itp.makeTerm("2"));
    }

    public void testTest2() {
        interpTestFail("test_2", itp.makeTerm("3"));
    }

    public void testTest3() {
        interpTestFail("test_3", itp.makeTuple("[]")); // , itp.makeTerm("3"));
    }

    public void testTestMap1() {
        interpTest("map_test_1", itp.makeList("[1,2,3]"), itp
                .makeList("[2,3,4]"));
    }

    public void testTestMap2() {
        interpTest("map_test_2", itp.makeList("[1,2,3]"), itp
                .makeList("[2,3,4]"));
    }

    public void testTestMap3() {
        interpTest("map_test_3", itp.makeTuple(itp.makeTerm(5), itp
                .makeList("[1,2,3]")), itp.makeList("[6,7,8]"));
    }

    public void testTestMap4() {
        interpTest("map_test_4", 
                   itp.makeList(itp.makeList("[1,2,3]"), 
                                itp.makeList("[2,3,4]")), 
                itp.makeList(itp.makeList("[2,3,4]"), 
                             itp.makeList("[3,4,5]")));
    }
}

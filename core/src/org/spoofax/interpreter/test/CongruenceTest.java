/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

public class CongruenceTest extends LanguageTest {

    public void testCongInt1() {
        interpTest("cong_int_1", "2", "2");
    }

    public void testCongInt2() {
        interpTestFail("cong_int_2", "3");
    }

    public void testCongString1() {
        interpTest("cong_string_1", "\"foo\"", "\"foo\"");
    }

    public void testCongString2() {
        interpTestFail("cong_string_2", "\"foo\"");
    }

    public void testCongTuple1() {
        interpTest("cong_tuple_1", itp.makeTuple("[3, 4]"), itp
                .makeTuple("[3, 4]"));
    }

    public void testCongTuple2() {
        interpTest("cong_tuple_2", itp.makeTuple("[3, 4]"), itp
                .makeTuple("[4, 5]"));
    }

    public void testCongTuple3() {
        interpTestFail("cong_tuple_3", itp.makeTuple("[3, 4]"));
    }

    public void testCongTuple4() {
        interpTest("cong_tuple_4", itp.makeTuple("[3, 4]"), itp
                .makeTuple("[3,5]"));
    }

    public void testCongTuple5() {
        interpTestFail("cong_tuple_5", itp.makeTuple("[3, 5]"));
    }

    public void testCongList1() {
        interpTest("cong_list_1", itp.makeList("[]"), itp.makeList("[]"));
    }

    public void testCongList2() {
        interpTest("cong_list_2", itp.makeList("[1]"), itp.makeList("[2]"));
    }

    public void testCongList3() {
        interpTest("cong_list_3", itp.makeList("[1,2]"), itp.makeList("[1,3]"));
    }

    public void testCongList4() {
        interpTest("cong_list_4", itp.makeList("[1]"), itp.makeList("[1]"));
    }

    public void testCongList5() {
        interpTest("cong_list_5", itp.makeList("[1,2]"), itp.makeList("[1]"));
    }

    public void testCongList6() {
        interpTestFail("cong_list_6", itp.makeList("[1,2]"));
    }

    public void testCongList7() {
        interpTestFail("cong_list_7", itp.makeList("[1,2]"));
    }

    public void testCongList8() {
        interpTestFail("cong_list_8", itp.makeList("[1,2]"));
    }

    public void testCongList9() {
        interpTest("cong_list_9", itp.makeList("[1,2]"), itp.makeList("[2]"));
    }

    public void testCongList10() {
        interpTest("cong_list_10", itp.makeList("[1,2]"), itp.makeTerm("2"));
    }

    public void testCongList11() {
        interpTestFail("cong_list_11", itp.makeList("[1]"));
    }

    public void testWrapSplit1() {
        interpTest("wrap_split_1", itp.makeTerm("2"), itp.makeTuple("[2,2]"));
    }

    public void testWrapSplit2() {
        interpTest("wrap_split_2", itp.makeTerm("2"), itp.makeTuple("[2,3]"));
    }

    public void testWrapSplit3() {
        interpTest("wrap_split_3", itp.makeTerm("2"), itp.makeTuple("[3,3]"));
    }

    public void testWrapSplit4() {
        interpTest("wrap_split_4", itp.makeTerm("2"), itp.makeList("[2,2]"));
    }

}

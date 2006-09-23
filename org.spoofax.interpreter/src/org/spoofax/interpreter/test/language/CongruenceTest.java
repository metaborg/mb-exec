/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test.language;

import org.spoofax.interpreter.test.AbstractLanguageTest;

public class CongruenceTest extends AbstractLanguageTest {

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
        interpTest("cong_tuple_1", "(3, 4)", "(3, 4)");
    }

    public void testCongTuple2() {
        interpTest("cong_tuple_2", "(3, 4)", "(4, 5)");
    }

    public void testCongTuple3() {
        interpTestFail("cong_tuple_3", "(3, 4)");
    }

    public void testCongTuple4() {
        interpTest("cong_tuple_4", "(3, 4)", "(3, 5)");
    }

    public void testCongTuple5() {
        interpTestFail("cong_tuple_5", "(3, 5)");
    }

    public void testCongList1() {
        interpTest("cong_list_1", "[]", "[]");
    }

    public void testCongList2() {
        interpTest("cong_list_2", "[1]", "[2]");
    }

    public void testCongList3() {
        interpTest("cong_list_3", "[1, 2]", "[1, 3]");
    }

    public void testCongList4() {
        interpTest("cong_list_4", "[1]", "[1]");
    }

    public void testCongList5() {
        interpTest("cong_list_5", "[1,2]", "[1]");
    }

    public void testCongList6() {
        interpTestFail("cong_list_6", "[1, 2]");
    }

    public void testCongList7() {
        interpTestFail("cong_list_7", "[1, 2]");
    }

    public void testCongList8() {
        interpTestFail("cong_list_8", "[1, 2]");
    }

    public void testCongList9() {
        interpTest("cong_list_9", "[1, 2]", "[2]");
    }

    public void testCongList10() {
        interpTest("cong_list_10", "[1, 2]", "2");
    }

    public void testCongList11() {
        interpTestFail("cong_list_11", "[1]");
    }

    public void testWrapSplit1() {
        interpTest("wrap_split_1", "2", "[2, 2]");
    }

    public void testWrapSplit2() {
        interpTest("wrap_split_2", "2", "[2, 3]");
    }

    public void testWrapSplit3() {
        interpTest("wrap_split_3", "2", "[3, 3]");
    }

    public void testWrapSplit4() {
        interpTest("wrap_split_4", "2", "[2, 2]");
    }

}

/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test.language;

import org.spoofax.interpreter.test.AbstractLanguageTest;

public class TestMatch extends AbstractLanguageTest {

    public void testMatchReal3() {
        interpTestFail("match_real_2", "2.0");
    }

    public void testMatchString1() {
        interpTest("match_string_1", "\"abc\"", "\"abc\"");
    }
    
    public void testMatchString1b() {
        interpTestFail("match_string_1", "abc()");
    }

    public void testMatchString2() {
        interpTestFail("match_string_2", "\"abc\"");
    }

    public void testMatchTuple1() {
        interpTest("match_tuple_1", "(1, 2)", "1");
    }

    public void testMatchTuple2() {
        interpTest("match_tuple_2", "(1, 2)", "2");
    }

    public void testMatchTuple3() {
        interpTest("match_tuple_3", "(2, 2)", "2");
    }

    public void testMatchTuple4() {
        interpTestFail("match_tuple_4", "(2, 3)");
    }

    public void testMatchInt1() {
        interpTest("match_int_1", "2", "2");
    }

    public void testMatchInt2() {
        interpTestFail("match_int_2", "3");
    }

    public void testMatchList1() {
        interpTest("match_list_1", "[1, 2]", "1");
    }
    
    public void testMatchList1b() {
        interpTestFail("match_list_1", "Cons(1, Cons(2, Nil))");
    }

    public void testMatchList2() {
        interpTest("match_list_2", "[1, 2]", "2");
    }

    public void testMatchList2b() {
        interpTestFail("match_list_2", "Cons(1, Cons(2, Nil))");
    }

    public void testMatchList3() {
        interpTest("match_list_3", "[2, 2]", "2");
    }

    public void testMatchList3b() {
        interpTestFail("match_list_3", "Cons(2, Cons(2, Nil))");
    }

    public void testMatchList4() {
        interpTestFail("match_list_4", "Cons(2, Cons(3, Nil))");
    }

    public void testMatchReal1() {
        interpTest("match_real_1", "2.0", "2.0");
    }

    public void testMatchReal2() {
        interpTest("match_real_2", "4.5", "4.5");
    }

    public void testAs1() {
        interpTest("as_1", "(1, 2)", "((1, 2), (1, 2))");
    }

    public void testAs2() {
        interpTest("as_2", "(1, 2)", "(1, 1)");
    }


    public void testMatchAndBuild1() {
        interpTest("match_and_build_1", "1", "1");
    }

    public void testMatchAndBuild2() {
        interpTestFail("match_and_build_2", "1");
    }

    public void testProject1() {
        interpTest("project_1", "(2, 3)", "2");
    }

    public void testProject2() {
        interpTest("project_2", "(2, 3)", "3");
    }

    public void testMatchAppl1() {
        interpTest("match_appl_1", "A()", "A()");
    }

    public void testMatchAppl1b() {
        interpTestFail("match_appl_1", "\"abc\"");
    }

    public void testMatchAppl1c() {
        interpTestFail("match_appl_1", "1");
    }

    public void testMatchAppl1d() {
        interpTestFail("match_appl_1", "1.0");
    }

    public void testMatchAppl1e() {
        interpTestFail("match_appl_1", "[]");
    }

    public void testMatchAppl1f() {
        interpTestFail("match_appl_1", "()");
    }

}

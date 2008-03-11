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
import org.spoofax.interpreter.test.AbstractLanguageTest;

public class TestMatch extends AbstractLanguageTest {

    public void testMatchReal3() throws IOException, InterpreterException {
        interpTestFail("match_real_2", "2.0");
    }

    public void testMatchString1() throws IOException, InterpreterException {
        interpTest("match_string_1", "\"abc\"", "\"abc\"");
    }
    
    public void testMatchString1b() throws IOException, InterpreterException {
        interpTestFail("match_string_1", "abc()");
    }
    
    public void testMatchString1c() throws IOException, InterpreterException {
        interpTestFail("match_string_1", "[]");
    }

    public void testMatchString1d() throws IOException, InterpreterException {
        interpTestFail("match_string_1", "1");
    }

    public void testMatchString1e() throws IOException, InterpreterException {
        interpTestFail("match_string_1", "()");
    }

    public void testMatchString1f() throws IOException, InterpreterException {
        interpTestFail("match_string_1", "1.0");
    }

    public void testMatchString2() throws IOException, InterpreterException {
        interpTestFail("match_string_2", "\"abc\"");
    }

    public void testMatchTuple1() throws IOException, InterpreterException {
        interpTest("match_tuple_1", "(1,2)", "1");
    }

    public void testMatchTuple1b() throws IOException, InterpreterException {
        interpTestFail("match_tuple_1", "[1,2]");
    }

    public void testMatchTuple1c() throws IOException, InterpreterException {
        interpTestFail("match_tuple_1", "\"foo\"");
    }

    public void testMatchTuple1d() throws IOException, InterpreterException {
        interpTestFail("match_tuple_1", "1");
    }

    public void testMatchTuple1e() throws IOException, InterpreterException {
        interpTestFail("match_tuple_1", "1.0");
    }

    public void testMatchTuple2() throws IOException, InterpreterException {
        interpTest("match_tuple_2", "(1,2)", "2");
    }

    public void testMatchTuple3() throws IOException, InterpreterException {
        interpTest("match_tuple_3", "(2,2)", "2");
    }

    public void testMatchTuple4() throws IOException, InterpreterException {
        interpTestFail("match_tuple_4", "(2,3)");
    }

    public void testMatchInt1() throws IOException, InterpreterException {
        interpTest("match_int_1", "2", "2");
    }

    public void testMatchInt2() throws IOException, InterpreterException {
        interpTestFail("match_int_2", "3");
    }

    public void testMatchList1() throws IOException, InterpreterException {
        interpTest("match_list_1", "[1,2]", "1");
    }
    
    public void testMatchList1b() throws IOException, InterpreterException {
        interpTestFail("match_list_1", "Cons(1,Cons(2,Nil))");
    }

    public void testMatchList2() throws IOException, InterpreterException {
        interpTest("match_list_2", "[1,2]", "2");
    }

    public void testMatchList2b() throws IOException, InterpreterException {
        interpTestFail("match_list_2", "Cons(1,Cons(2,Nil))");
    }

    public void testMatchList3() throws IOException, InterpreterException {
        interpTest("match_list_3", "[2,2]", "2");
    }

    public void testMatchList3b() throws IOException, InterpreterException {
        interpTestFail("match_list_3", "Cons(2,Cons(2,Nil))");
    }

    public void testMatchList4() throws IOException, InterpreterException {
        interpTestFail("match_list_4", "Cons(2,Cons(3,Nil))");
    }

//    public void testMatchList4b() throws IOException, InterpreterException {
//        interpTest("match_list_4", "Cons(3, Cons(3, Nil))", "[3,3]");
//    }

    public void testMatchList4c() throws IOException, InterpreterException {
        interpTestFail("match_list_4", "\"foo\"");
    }

    public void testMatchList4d() throws IOException, InterpreterException {
        interpTestFail("match_list_4", "1");
    }

    public void testMatchList4e() throws IOException, InterpreterException {
        interpTestFail("match_list_4", "1.0");
    }
    
    public void testMatchList5() throws IOException, InterpreterException {
        interpTest("match_list_5", "[1,1]", "[1,1]");
    }

    public void testMatchList6() throws IOException, InterpreterException {
        interpTest("match_list_6", "[1]", "[1]");
    }

    public void testMatchList7() throws IOException, InterpreterException {
        interpTest("match_list_7", "[1,2,3,4]", "[2,3,4]");
    }

    public void testMatchListTuple1() throws IOException, InterpreterException {
        interpTest("match_list_tuple_1", "([1,2,3,4],[2,3,4,5])", "([2,3,4],[3,4,5])");
    }

    public void testMatchListTuple2() throws IOException, InterpreterException {
        interpTest("match_list_tuple_2", "([1,2,3,4],[1,3,4,5])", "([2,3,4],[3,4,5])");
    }

    public void testMatchListTuple2b() throws IOException, InterpreterException {
        interpTestFail("match_list_tuple_2", "([1,2,3,4],[2,3,4,5])");
    }

    public void testMatchReal1() throws IOException, InterpreterException {
        interpTest("match_real_1", "2.0", "2.0");
    }

    public void testMatchReal2() throws IOException, InterpreterException {
        interpTest("match_real_2", "4.5", "4.5");
    }

    public void testAs1() throws IOException, InterpreterException {
        interpTest("as_1", "(1,2)", "((1,2),(1,2))");
    }

    public void testAs2() throws IOException, InterpreterException {
        interpTest("as_2", "(1,2)", "(1,1)");
    }

    public void testAs3() throws IOException, InterpreterException {
        interpTest("as_3", "(1,2)", "((1,2),1)");
    }

    public void testAs4() throws IOException, InterpreterException {
        interpTest("as_4", "2", "(2,2)");
    }

    public void testMatchAndBuild1() throws IOException, InterpreterException {
        interpTest("match_and_build_1", "1", "1");
    }

    public void testMatchAndBuild2() throws IOException, InterpreterException {
        interpTestFail("match_and_build_2", "1");
    }

    public void testProject1() throws IOException, InterpreterException {
        interpTest("project_1", "(2,3)", "2");
    }

    public void testProject2() throws IOException, InterpreterException {
        interpTest("project_2", "(2,3)", "3");
    }

    public void testMatchAppl1() throws IOException, InterpreterException {
        interpTest("match_appl_1", "A()", "A()");
    }

    public void testMatchAppl1b() throws IOException, InterpreterException {
        interpTestFail("match_appl_1", "\"abc\"");
    }

    public void testMatchAppl1c() throws IOException, InterpreterException {
        interpTestFail("match_appl_1", "1");
    }

    public void testMatchAppl1d() throws IOException, InterpreterException {
        interpTestFail("match_appl_1", "1.0");
    }

    public void testMatchAppl1e() throws IOException, InterpreterException {
        interpTestFail("match_appl_1", "[]");
    }

    public void testMatchAppl1f() throws IOException, InterpreterException {
        interpTestFail("match_appl_1", "()");
    }
    
    public void testZ() throws IOException {
        //System.in.read();
    }
}

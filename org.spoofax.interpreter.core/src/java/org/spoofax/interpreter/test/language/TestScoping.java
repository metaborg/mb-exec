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

public class TestScoping extends AbstractLanguageTest {

    public void testScopeMatchInterrupt1() throws IOException, InterpreterException {
        interpTest("match_interrupted_by_scope_1", "2", "2");
    }

    public void testScopeMatchInterrupt2() throws IOException, InterpreterException {
        interpTest("match_interrupted_by_scope_2", "2", "2");
    }

    public void testScopeMatchInterrupt3() throws IOException, InterpreterException {
        interpTest("match_interrupted_by_scope_3", "3", "3");
    }

    public void testScopeMatchInterrupt4() throws IOException, InterpreterException {
        interpTest("match_interrupted_by_scope_4", "4", "4");
    }

    public void testChoiceDoNotUnbinding() throws IOException, InterpreterException {
        interpTest("do_not_unbinding_lhs_of_lchoice_if_it_succeeds", "()", "1");
    }

    public void testChoiceUnbinding() throws IOException, InterpreterException {
        interpTest("unbinding_in_lchoice", "2", "3");
    }

    public void testUnbinding3() throws IOException, InterpreterException {
        interpTestFail("unbinding_3", "()");
    }

    public void testLet1() throws IOException, InterpreterException {
        interpTest("let_test_1", "1", "2");
    }

    public void testLet2() throws IOException, InterpreterException {
        interpTest("let_test_2", "1", "2");
    }

}

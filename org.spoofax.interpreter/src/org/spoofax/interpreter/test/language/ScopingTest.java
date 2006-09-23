/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test.language;

import org.spoofax.interpreter.test.AbstractLanguageTest;

public class ScopingTest extends AbstractLanguageTest {

    public void testScopeMatchInterrupt1() {
        interpTest("match_interrupted_by_scope_1", "2", "2");
    }

    public void testScopeMatchInterrupt2() {
        interpTest("match_interrupted_by_scope_2", "2", "2");
    }

    public void testScopeMatchInterrupt3() {
        interpTest("match_interrupted_by_scope_3", "3", "3");
    }

    public void testScopeMatchInterrupt4() {
        interpTest("match_interrupted_by_scope_4", "4", "4");
    }

    public void testChoiceDoNotUnbinding() {
        interpTest("do_not_unbinding_lhs_of_lchoice_if_it_succeeds", "()", "1");
    }

    public void testChoiceUnbinding() {
        interpTest("unbinding_in_lchoice", "2", "3");
    }

    public void testLet1() {
        interpTest("let_test_1", "1", "2");
    }

    public void testLet2() {
        interpTest("let_test_2", "1", "2");
    }

}

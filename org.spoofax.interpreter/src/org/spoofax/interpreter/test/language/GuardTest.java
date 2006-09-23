/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test.language;

import org.spoofax.interpreter.test.AbstractLanguageTest;

public class GuardTest extends AbstractLanguageTest {

    public void testGuarded1() {
           interpTest("guarded_1", "()", "1");
       }

    public void testGuarded2() {
           interpTest("guarded_2", "()", "2");
       }

    public void testGuarded3() {
           interpTest("guarded_3", "()", "3");
       }

    public void testGuarded4() {
           interpTest("guarded_4", "5", "5");
       }

    public void testGuarded5() {
           interpTest("guarded_5", "()", "1");
       }

    public void testGuarded6() {
           interpTest("guarded_6", "()", "1");
       }

    public void testGuarded7() {
           interpTest("guarded_7", "()", "2");
       }

    public void testLeftChoiceGuard() {
        interpTest("guarded_modifies_current_term", 
                   "()", 
                   "3");
    }

}

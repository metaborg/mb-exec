/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

public class GuardTest extends AbstractLanguageTest {

    public void testGuarded1() {
           interpTest("guarded_1", itp.makeTuple("[]"), itp.makeTerm("1"));
       }

    public void testGuarded2() {
           interpTest("guarded_2", itp.makeTuple("[]"), itp.makeTerm("2"));
       }

    public void testGuarded3() {
           interpTest("guarded_3", itp.makeTuple("[]"), itp.makeTerm("3"));
       }

    public void testGuarded4() {
           interpTest("guarded_4", itp.makeTerm("5"), itp.makeTerm("5"));
       }

    public void testGuarded5() {
           interpTest("guarded_5", itp.makeTuple("[]"), itp.makeTerm("1"));
       }

    public void testGuarded6() {
           interpTest("guarded_6", itp.makeTuple("[]"), itp.makeTerm("1"));
       }

    public void testGuarded7() {
           interpTest("guarded_7", itp.makeTuple("[]"), itp.makeTerm("2"));
       }

    public void testLeftChoiceGuard() {
        interpTest("guarded_modifies_current_term", itp.makeTuple("[]"), itp
                .makeTerm("3"));
    }

}

/*
 * Created on 02.mar.2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test.language;

import org.spoofax.interpreter.test.AbstractLanguageTest;

public class TestHigherOrderParameters extends AbstractLanguageTest {

    public void testHigherOrderParam1() {
        interpTest("higher_order_param_1", "()", "5");
    }

    public void testHigherOrderParam2() {
        interpTest("higher_order_param_2", "()", "5");
    }
    
    public void testHigherOrderParam3() {
        interpTest("higher_order_param_3", "()", "5");
    }
    
    public void testHigherOrderParam4() {
        interpTest("higher_order_param_4", "()", "5");
    }
}

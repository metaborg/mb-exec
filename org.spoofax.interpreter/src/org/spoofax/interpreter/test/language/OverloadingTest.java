/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test.language;

import org.spoofax.interpreter.test.AbstractLanguageTest;

public class OverloadingTest extends AbstractLanguageTest {

    public void testOverloading1() {
        interpTest("overloading_1", "()", "1");
    }

    public void testOverloading2() {
        interpTest("overloading_2", "()", "2");
    }

    public void testOverloading3() {
        interpTest("overloading_3", "()", "1");
    }

    public void testOverloading4() {
        interpTest("overloading_4", "()", "2");
    }

}

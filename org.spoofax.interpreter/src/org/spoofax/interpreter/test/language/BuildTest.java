/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test.language;

import org.spoofax.interpreter.test.AbstractLanguageTest;

public class BuildTest extends AbstractLanguageTest {

    public void testBuildInt() {
        interpTest("build_int", "()", "5");
    }

    public void testBuildReal() {
        interpTest("build_real", "()", "5.0");
    }

    public void testBuildString() {
        interpTest("build_string", "()", "\"a\"");
    }

    public void testBuildTuple() {
        interpTest("build_tuple", "()", "(2, 3)");
    }

    public void testBuildList1() {
        interpTest("build_list_1", "()", "[]");
    }

    public void testBuildList2() {
        interpTest("build_list_2", "()", "[1,2,3]");
    }

}

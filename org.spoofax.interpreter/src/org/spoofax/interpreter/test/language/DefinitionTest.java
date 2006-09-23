/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test.language;

import org.spoofax.interpreter.test.AbstractLanguageTest;

public class DefinitionTest extends AbstractLanguageTest {

    public void testRDef1() {
        interpTest("foo_rdef_1", "1", "2");
    }

    public void testRDef2() {
        interpTest("foo_rdef_2", "()", "(2,1)");
    }

    public void testRDef3() {
        interpTest("foo_rdef_3", "()", "2");
    }

    public void testRDef4() {
        interpTestFail("foo_rdef_4", "()");
    }

    public void testSDef1() {
        interpTest("foo_sdef_1", "()", "3");
    }

    public void testSDef2() {
        interpTest("foo_sdef_2", "()", "3");
    }

    public void testSDef3() {
        interpTest("foo_sdef_3", "()", "3");
    }

}

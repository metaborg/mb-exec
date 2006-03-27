/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

public class DefinitionTest extends AbstractLanguageTest {

    public void testRDef1() {
        interpTest("foo_rdef_1", itp.makeTerm("1"), itp.makeTerm("2"));
    }

    public void testRDef2() {
        interpTest("foo_rdef_2", itp.makeTuple("[]"), itp.makeTuple("[2, 1]"));
    }

    public void testRDef3() {
        interpTest("foo_rdef_3", itp.makeTuple("[]"), itp.makeTerm("2"));
    }

    public void testRDef4() {
        interpTestFail("foo_rdef_4", itp.makeTuple("[]"));
    }

    public void testSDef1() {
        interpTest("foo_sdef_1", itp.makeTuple("[]"), itp.makeTerm("3"));
    }

    public void testSDef2() {
        interpTest("foo_sdef_2", itp.makeTuple("[]"), itp.makeTerm("3"));
    }

    public void testSDef3() {
        interpTest("foo_sdef_3", itp.makeTuple("[]"), itp.makeTerm("3"));
    }

}

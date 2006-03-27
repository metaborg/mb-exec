/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

public class BuildTest extends AbstractLanguageTest {

    public void testBuildInt() {
        interpTest("build_int", "1", "5");
    }

    public void testBuildReal() {
        interpTest("build_real", itp.makeTuple("[]"), itp.makeTerm("5.0"));
    }

    public void testBuildString() {
        interpTest("build_string", "1", "\"a\"");
    }

    public void testBuildTuple() {
        interpTest("build_tuple", itp.makeTerm("1"), itp.makeTuple("[2, 3]"));
    }

    public void testBuildList1() {
        interpTest("build_list_1", itp.makeTerm("1"), itp.makeList("[]"));
    }

    public void testBuildList2() {
        interpTest("build_list_2", itp.makeTerm("1"), itp.makeList("[1,2,3]"));
    }

}

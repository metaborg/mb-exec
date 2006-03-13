/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

public class OverloadingTest extends LanguageTest {

    public void testOverloading1() {
        interpTest("overloading_1", itp.makeTuple("[]"), itp.makeTerm("1"));
    }

    public void testOverloading2() {
        interpTest("overloading_2", itp.makeTuple("[]"), itp.makeTerm("2"));
    }

    public void testOverloading3() {
        interpTest("overloading_3", itp.makeTuple("[]"), itp.makeTerm("1"));
    }

    public void testOverloading4() {
        interpTest("overloading_4", itp.makeTuple("[]"), itp.makeTerm("2"));
    }

}

/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

public class TermDeconstructionTest extends LanguageTest {

    public void testTermDeconstr1() {
           interpTest("term_deconstr_1", itp.makeTerm("1"), itp.makeTerm("1"));
       }

    public void testTermDeconstr2() {
           interpTest("term_deconstr_2", itp.makeTerm("Plus(1,2)"), itp.makeList("[2]"));
       }

    public void testTermDeconstr3() {
           interpTest("term_deconstr_3", itp.makeTerm("Plus(1,2)"), itp.makeList("[1,2]"));
       }

    public void testTermDeconstr4() {
           interpTest("term_deconstr_4", itp.makeTerm("Plus(1,2)"), itp.makeList("[1,2]"));
       }

    public void testTermDeconstr5() {
           interpTest("term_deconstr_4", itp.makeTerm("Pair(1,2)"), itp.makeTerm("Pair(1,2)"));
       }

    public void testTermArg1() {
        interpTest("id_term-arg_1", itp.makeTuple("[]"), itp.makeTerm("3"));
    }

    public void testTermSize() {
        interpTest("term-size", itp.makeTerm("2"), itp.makeTerm("1"));
    }

}

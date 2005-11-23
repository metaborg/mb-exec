/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

public class TermDeconstructionTest extends LanguageTest {

    public void testTermDeconstr1a() {
        interpTest("term_deconstr_1", itp.makeTerm("1"), itp.makeTerm("1"));
    }

    public void testTermDeconstr1b() {
        interpTest("term_deconstr_3", itp.makeTerm("1"), itp.makeList("[]"));
    }

    public void testTermDeconstr2a() {
        interpTest("term_deconstr_1", itp.makeTerm("Plus(1,2)"), itp
                .makeTerm("\"Plus\""));
    }

    public void testTermDeconstr2b() {
        interpTest("term_deconstr_3", itp.makeTerm("Plus(1,2)"), itp
                .makeList("[1,2]"));
    }

    public void testTermDeconstr3() {
        interpTest("term_deconstr_2", itp.makeTerm("Plus(1,2)"), itp
                .makeList("[2]"));
    }

    public void testTermDeconstr4() {
        interpTest("term_deconstr_4", itp.makeTerm("Plus(1,2)"), itp
                .makeList("[1,2]"));
    }

    public void testTermDeconstr5() {
        interpTest("term_deconstr_5", itp.makeTerm("Pair(1,2)"), itp
                .makeTerm("Pair(1,2)"));
    }

    public void testTermDeconstr6a() {
        interpTest("term_deconstr_1", itp.makeTerm("4.5"), itp.makeTerm("4.5"));
    }

    public void testTermDeconstr6b() {
        interpTest("term_deconstr_3", itp.makeTerm("4.5"), itp.makeList("[]"));
    }

    public void testTermDeconstr7a() {
        interpTest("term_deconstr_1", itp.makeTerm("\"foo\""), itp
                .makeTerm("\"\\\"foo\\\"\""));
    }

    public void testTermDeconstr7b() {
        interpTest("term_deconstr_3", itp.makeTerm("\"foo\""), itp
                .makeList("[]"));
    }

    public void testTermDeconstr8a() {
        interpTest("term_deconstr_1", itp.makeList("[1,2,3]"), itp
                .makeList("[1,2,3]"));
    }

    public void testTermDeconstr8b() {
        interpTest("term_deconstr_3", itp.makeList("[1,2,3]"), itp
                .makeList("[]"));
    }

    public void testTermDeconstr9a() {
        interpTest("term_deconstr_1", itp.makeTuple("[1,2,3]"), itp
                .makeTerm("\"\""));
    }

    public void testTermDeconstr9b() {
        interpTest("term_deconstr_3", itp.makeTuple("[1,2,3]"), itp
                .makeList("[1,2,3]"));
    }

    public void testTermSize() {
        interpTest("term-size", itp.makeTerm("2"), itp.makeTerm("1"));
    }

    public void testTermArg1() {
        interpTest("id_term-arg_1", itp.makeTuple("[]"), itp.makeTerm("3"));
    }

}

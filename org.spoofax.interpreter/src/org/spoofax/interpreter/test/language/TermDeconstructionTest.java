/*
 * Created on 23.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test.language;

import org.spoofax.interpreter.test.AbstractLanguageTest;

public class TermDeconstructionTest extends AbstractLanguageTest {

    public void testTermDeconstr1a() {
        interpTest("term_deconstr_1", "1", "1");
    }

    public void testTermDeconstr1b() {
        interpTest("term_deconstr_3", "1", "[]");
    }

    public void testTermDeconstr2a() {
        interpTest("term_deconstr_1", 
                   "Plus(1, 2)", "\"Plus\"");
    }

    public void testTermDeconstr2b() {
        interpTest("term_deconstr_3", "Plus(1, 2)", "[1, 2]");
    }

    public void testTermDeconstr3() {
        interpTest("term_deconstr_2", "Plus(1, 2)", "[2]");
    }

    public void testTermDeconstr4() {
        interpTest("term_deconstr_4", "Plus(1, 2)", "[1, 2]");
    }

    public void testTermDeconstr5() {
        interpTest("term_deconstr_5", "Pair(1, 2)", "Pair(1, 2)");
    }

    public void testTermDeconstr6a() {
        interpTest("term_deconstr_1", "4.5", "4.5");
    }

    public void testTermDeconstr6b() {
        interpTest("term_deconstr_3", "4.5", "[]");
    }

    public void testTermDeconstr7a() {
        interpTest("term_deconstr_1", "\"foo\"", "\"\\\"foo\\\"\"");
    }

    public void testTermDeconstr7b() {
        interpTest("term_deconstr_3", "\"foo\"", "[]");
    }

    public void testTermDeconstr8a() {
        interpTest("term_deconstr_1", "[1,2,3]", "[]");
    }

    public void testTermDeconstr8b() {
        interpTest("term_deconstr_3", "[1,2,3]", "[1,2,3]");
    }

    public void testTermDeconstr9a() {
        interpTest("term_deconstr_1", "[1,2,3]", "\"\"");
    }

    public void testTermDeconstr9b() {
        interpTest("term_deconstr_3", "[1,2,3]", "[1,2,3]");
    }

    public void testTermSize() {
        interpTest("term-size", "2", "1");
    }

    public void testTermArg1() {
        interpTest("id_term-arg_1", "()", "3");
    }

}

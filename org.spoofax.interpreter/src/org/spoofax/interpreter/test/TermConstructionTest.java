/*
 * Created on 24.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

import aterm.ATerm;

public class TermConstructionTest extends AbstractLanguageTest {

    public void testTermConstr1() {
        interpTest("term_constr_1", itp.makeTuple("[]"), itp
                .makeTerm("Pair(1,2)"));
    }

    public void testTermConstr2() {
        interpTest("term_constr_2", itp.makeTuple("[]"), itp
                .makeTuple("[1,2,3]"));
    }

    public void testTermConstr3() {
        interpTest("term_constr_3", itp.makeTuple("[]"), itp
                .makeList("[1,2,3]"));
    }

    public void testTermConstr4() {
        interpTest("term_constr_4", itp.makeTuple("[]"), itp.makeTerm("1"));
    }

    public void testTermConstr5() {
        interpTest("term_constr_5", itp.makeTuple("[]"), itp.makeTerm("4.5"));
    }

    public void testTermConstr6() {
        interpTest("term_constr_6", 
                   itp.makeTuple(itp.makeTerm("\"Pair\""), itp
                .makeList("[3,4]")),
                itp.makeTerm("Pair(3,4)"));
    }

    public void testTermConstr7() {
        interpTest("term_constr_6", 
                   itp.makeTuple(itp.makeTerm("1"), itp
                .makeList("[1,2,3,4]")),
                itp.makeTerm("1"));
    }

    public void testTermConstr8() {
        ATerm s = itp.makeString("\"foo\"");
        System.out.println(s);
        interpTest("term_constr_6", 
                   itp.makeTuple(s, itp
                .makeList("[]")),
                itp.makeTerm("\"foo\""));
    }
}

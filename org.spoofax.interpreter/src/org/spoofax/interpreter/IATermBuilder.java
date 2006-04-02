/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

import java.util.Collection;

import aterm.ATerm;
import aterm.ATermList;
import aterm.AFun;

public interface IATermBuilder {

    ATerm makeList(ATermList terms);

    ATerm makeList(ATerm... terms);

    ATerm makeList(Collection<ATerm> terms);

    ATerm makeTerm(String s);

    ATerm makeTerm(int i);

    ATerm makeString(String s);

    //todo: move these in a separate interface?
    AFun getOpAFun();

    AFun getConsAFun();

    AFun getNilAFun();

    AFun getAnnoAFun();

    AFun getStrAFun();

    AFun getVarAFun();

    AFun getExplodeAFun();

    AFun getRealAFun();

    AFun getIntAFun();

    AFun getConstTypeAFun();

    AFun getFunTypeAFun();

    AFun getExtSDefAFun();

    AFun getSDefTAFun();

    AFun getAsAFun();

    AFun getWldAFun();
}

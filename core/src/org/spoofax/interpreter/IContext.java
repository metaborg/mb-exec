/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

import java.util.List;

import org.spoofax.interpreter.stratego.SDefT;

import aterm.ATerm;
import aterm.ATermList;
import aterm.pure.PureFactory;

public interface IContext extends IATermBuilder {

    ATerm current();
    void setCurrent(ATerm newCurrent);
    
    public ATerm lookupVar(String n) throws FatalError;
    public SDefT lookupSVar(String n) throws FatalError;
    
    TermFactory getFactory();
    
    boolean bindVars(List<Pair<String, ATerm>> r);

    ATerm makeTerm(String s);
    ATerm makeList(ATermList args);
    
    VarScope getVarScope();
    void setVarScope(VarScope newVarScope);
}

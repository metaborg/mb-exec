/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter;

import java.util.List;

import org.spoofax.interpreter.stratego.SDefT;

import aterm.ATerm;
import aterm.ATermList;
import aterm.pure.PureFactory;

public interface IEnvironment {

    ATerm current();
    void setCurrent(ATerm newCurrent);
    
    public ATerm lookupVar(String n) throws FatalError;
    public SDefT lookupSVar(String n) throws FatalError;
    PureFactory getFactory();
    void dumpScope(String string);
    boolean bindVars(List<Pair<String, ATerm>> r);
    ATerm makeTerm(String string);
    ATerm makeList(ATermList args);
    VarScope getVarScope();
    DefScope getDefScope();
    void setVarScope(VarScope newVarScope);
    void setDefScope(DefScope newDefScope);

}

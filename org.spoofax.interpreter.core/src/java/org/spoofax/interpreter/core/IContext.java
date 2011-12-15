/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2
 */
package org.spoofax.interpreter.core;

import java.util.Collection;

import org.spoofax.IAsyncCancellable;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.library.IOperatorRegistry;
import org.spoofax.interpreter.stratego.Match.Results;
import org.spoofax.interpreter.stratego.SDefT;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public interface IContext extends IAsyncCancellable {

    public IStrategoTerm current();
    public void setCurrent(IStrategoTerm newCurrent);

    public IStrategoTerm lookupVar(String n) throws InterpreterException;
    public SDefT lookupSVar(String n) throws InterpreterException;

    public ITermFactory getFactory();
    public void setFactory(ITermFactory factory);
    public IOperatorRegistry getOperatorRegistry(String domain);
    public void addOperatorRegistry(IOperatorRegistry or);

    public boolean bindVars(Results r);

    public VarScope getVarScope();
    public void setVarScope(VarScope newVarScope);

    public void popVarScope();
    public void restoreVarScope(VarScope anotherVarScope);

    public StrategoSignature getStrategoSignature();
    public AbstractPrimitive lookupOperator(String name);
    public Collection<String> getStrategyNames();

    public StackTracer getStackTracer();
}

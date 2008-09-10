/*
 * Created on 28. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;

import org.spoofax.NotImplementedException;

public class BasicStrategoConstructor extends BasicStrategoTerm implements IStrategoConstructor {

    private final String name;
    private final int arity;

    public BasicStrategoConstructor(String name, int arity) {
        super(null);
        this.name = name;
        this.arity = arity;
    }
    
    public int getArity() {
        return arity;
    }

    public String getName() {
        return name;
    }

    public IStrategoAppl instantiate(ITermFactory factory, IStrategoTerm... kids) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public IStrategoAppl instantiate(ITermFactory factory, IStrategoList kids) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public IStrategoTerm[] getAllSubterms() {
        return BasicTermFactory.EMPTY;
    }
    
    @Override
    public IStrategoList getAnnotations() {
        throw new UnsupportedOperationException();
    }

    public IStrategoTerm getSubterm(int index) {
        throw new ArrayIndexOutOfBoundsException();
    }

    public int getSubtermCount() {
        return 0;
    }

    public int getTermType() {
        return IStrategoTerm.CTOR;
    }

    @Override
    protected boolean doSlowMatch(IStrategoTerm second) {
        if(second.getTermType() != IStrategoTerm.CTOR)
            return false;
        IStrategoConstructor o = (IStrategoConstructor)second;
        return arity == o.getArity() && name.equals(o.getName());
    }

    public void prettyPrint(ITermPrinter pp) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }
    
    @Override
    public int hashCode() {
        return name.hashCode() + 5407 * arity; 
    }
}

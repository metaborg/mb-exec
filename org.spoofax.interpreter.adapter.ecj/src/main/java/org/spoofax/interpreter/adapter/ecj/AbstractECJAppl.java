/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermPrinter;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.skeleton.SkeletonStrategoAppl;

public abstract class AbstractECJAppl extends SkeletonStrategoAppl {

    private static final long serialVersionUID = 1L;

    private final IStrategoConstructor constructor;
    
    protected AbstractECJAppl(IStrategoConstructor constructor) {
        super(TermFactory.EMPTY_LIST, IStrategoTerm.IMMUTABLE);
    
        this.constructor = constructor;
    }
    
    @Override
    public IStrategoConstructor getConstructor() {
        return constructor;
    }

    @Override
    public abstract IStrategoTerm getSubterm(int index);

    @Override
    public IStrategoTerm[] getAllSubterms() {
        final int sz = getConstructor().getArity();
        IStrategoTerm[] r = new IStrategoTerm[sz];
        for(int i = 0; i < sz; i++) {
            r[i] = getSubterm(i);
        }
        return r;
    }
    
    @Override
    public void prettyPrint(ITermPrinter pp) {
        throw new NotImplementedException();
    }
}

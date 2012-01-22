/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.skeleton.SkeletonStrategoAppl;

public abstract class WrappedASMNode extends SkeletonStrategoAppl {

	private static final long serialVersionUID = 1L;
	
	private final IStrategoConstructor constructor;
	
	WrappedASMNode(final IStrategoConstructor constructor) {
		super(TermFactory.EMPTY_LIST, IStrategoTerm.IMMUTABLE);
		this.constructor = constructor;
	}
	
    public IStrategoTerm[] getAllSubterms() {
        final int sz = constructor.getArity();
        IStrategoTerm[] r = new IStrategoTerm[sz];
        for(int i = 0; i < sz; i++) {
            r[i] = getSubterm(i);
        }
        return r;
    }
    
    @Override
    public IStrategoConstructor getConstructor() {
    	return constructor;
    }
}

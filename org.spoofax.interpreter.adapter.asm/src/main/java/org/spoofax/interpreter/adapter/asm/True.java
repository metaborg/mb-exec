/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.skeleton.SkeletonStrategoAppl;

public class True extends SkeletonStrategoAppl implements ASMBooleanValue {

	private static final long serialVersionUID = 2376503069060203251L;
	
	private final static IStrategoTerm[] EMPTY = new IStrategoTerm[0];
	private final static IStrategoConstructor CTOR = new StrategoConstructor("True", 0); 
    
    final static True INSTANCE = new True();
    
    protected True() {
    	super(TermFactory.EMPTY_LIST, IStrategoTerm.IMMUTABLE);
    }
    
    @Override
    public IStrategoConstructor getConstructor() {
    	return CTOR;
    }

	@Override
	public IStrategoTerm getSubterm(int index) {
		throw new ArrayIndexOutOfBoundsException(index);
	}

	@Override
	public IStrategoTerm[] getAllSubterms() {
		return EMPTY;
	}
}

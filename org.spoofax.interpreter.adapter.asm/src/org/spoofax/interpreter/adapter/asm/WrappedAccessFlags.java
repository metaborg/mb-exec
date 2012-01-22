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

public class WrappedAccessFlags extends SkeletonStrategoAppl {

	private static final long serialVersionUID = 1114963759066088382L;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("AccessFlags", 1);
	private final int wrappee;
	
	public WrappedAccessFlags(int wrappee) {
		super(TermFactory.EMPTY_LIST, IStrategoTerm.IMMUTABLE);
		this.wrappee = wrappee;
	}

	@Override
	public IStrategoConstructor getConstructor() {
		return CTOR;
	}

	@Override
	public IStrategoTerm getSubterm(int index) {
		if (index == 0)
			return ASMFactory.wrap(wrappee);
		throw new ArrayIndexOutOfBoundsException(index);
	}

	@Override
	public IStrategoTerm[] getAllSubterms() {
		return new IStrategoTerm[] { getSubterm(0) };
	}

}

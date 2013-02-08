/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.skeleton.SkeletonStrategoList;

public class ASMArray extends SkeletonStrategoList {

	private static final long serialVersionUID = -4370875591624520389L;
	
	private final Object[] wrappee;

	ASMArray(Object[] wrappee) {
		super(TermFactory.EMPTY_LIST, IStrategoTerm.IMMUTABLE);
		this.wrappee = wrappee;
	}

	@Override
	public IStrategoTerm getSubterm(int index) {
		return ASMFactory.genericWrap(wrappee[index]);
	}

	@Override
	public IStrategoTerm head() {
		throw new NotImplementedException();
	}

	@Override
	public int getSubtermCount() {
		return wrappee.length;
	}

	public IStrategoList tail() {
		throw new NotImplementedException();
	}

	public IStrategoTerm[] getAllSubterms() {
		IStrategoTerm[] ret = new IStrategoTerm[wrappee.length];
		for(int i = 0; i < ret.length; i++)
			ret[i] = ASMFactory.genericWrap(wrappee[i]);
		return ret;
	}

	@Override
	public boolean isEmpty() {
		return getSubtermCount() == 0;
	}
}

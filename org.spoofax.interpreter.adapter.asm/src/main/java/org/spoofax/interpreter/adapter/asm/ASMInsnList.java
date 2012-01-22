/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.InsnList;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.skeleton.SkeletonStrategoList;

public class ASMInsnList extends SkeletonStrategoList {

	private static final long serialVersionUID = 4221469797997265710L;
	
	private InsnList wrappee;

	ASMInsnList(InsnList wrappee) {
		super(TermFactory.EMPTY_LIST, IStrategoTerm.IMMUTABLE);
		this.wrappee = wrappee;
	}

	@Override
	public IStrategoTerm getSubterm(int index) {
		return ASMFactory.genericWrap(wrappee.get(index));
	}

	@Override
	public IStrategoTerm head() {
		return ASMFactory.genericWrap(wrappee.get(0));
	}

	@Override
	public boolean isEmpty() {
		return wrappee.size() == 0;
	}

	@Override
	public IStrategoList tail() {
		throw new NotImplementedException();
	}

	@Override
	public IStrategoTerm[] getAllSubterms() {
		IStrategoTerm[] ret = new IStrategoTerm[wrappee.size()];
		Object[] xs = wrappee.toArray();
		for(int i = 0, c = xs.length; i < c; i++) {
			ret[i] = ASMFactory.genericWrap(xs[i]);
		}
		return ret;
	}

	@Override
	public int getSubtermCount() {
		return wrappee.size();
	}
}

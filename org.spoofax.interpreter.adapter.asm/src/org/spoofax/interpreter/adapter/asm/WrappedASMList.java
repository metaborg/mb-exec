/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import java.util.ArrayList;
import java.util.List;

import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.skeleton.SkeletonStrategoList;

public class WrappedASMList extends SkeletonStrategoList {

	private static final long serialVersionUID = 3536693350501701065L;
	
	private List<Object> wrappee;

	protected WrappedASMList(List<Object> wrappee) {
		super(TermFactory.EMPTY_LIST, IStrategoTerm.IMMUTABLE);
		this.wrappee = wrappee;
	}

	public IStrategoTerm head() {
		return ASMFactory.genericWrap(wrappee.get(0));
	}

	public boolean isEmpty() {
		return wrappee.isEmpty();
	}

	public IStrategoList tail() {
		List<Object> ret = new ArrayList<Object>(wrappee);
		ret.remove(ret.size() - 1);
		return new WrappedASMList(ret);
	}

	public IStrategoTerm[] getAllSubterms() {
		IStrategoTerm[] ret = new IStrategoTerm[wrappee.size()];
		int count = 0;
		for(Object o : wrappee) {
			ret[count++] = ASMFactory.genericWrap(o);
		}
		return ret;
	}

	public IStrategoTerm getSubterm(int index) {
		return ASMFactory.genericWrap(wrappee.get(index));
	}

	public int getSubtermCount() {
		return wrappee.size();
	}
}

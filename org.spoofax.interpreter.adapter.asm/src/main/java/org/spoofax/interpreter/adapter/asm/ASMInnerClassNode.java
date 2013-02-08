/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.InnerClassNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMInnerClassNode extends AbstractASMNode {

	private static final long serialVersionUID = -3302267017429134664L;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("InnerClassNode", 4);
	private final InnerClassNode wrappee;

	ASMInnerClassNode(InnerClassNode wrappee) {
		super(CTOR);
		this.wrappee = wrappee;
	}
	
	@Override
	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrapAccessFlags(wrappee.access);
		case 1:
			return ASMFactory.wrap(wrappee.innerName);
		case 2:
			return ASMFactory.wrap(wrappee.name);
		case 3:
			return ASMFactory.wrap(wrappee.outerName);
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

}

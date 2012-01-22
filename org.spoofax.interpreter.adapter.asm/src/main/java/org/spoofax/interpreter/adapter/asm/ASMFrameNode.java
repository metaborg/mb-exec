/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.FrameNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMFrameNode extends WrappedASMNode {

	private static final long serialVersionUID = 5861904289421707200L;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("FrameNode", 3);
	
	private final FrameNode wrappee;
	
	ASMFrameNode(FrameNode wrappee) {
		super(CTOR);
		this.wrappee = wrappee;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrapOpcode(wrappee.getOpcode());
		case 1:
			return ASMFactory.wrap(wrappee.local);
		case 2:
			return ASMFactory.wrap(wrappee.stack);
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

}

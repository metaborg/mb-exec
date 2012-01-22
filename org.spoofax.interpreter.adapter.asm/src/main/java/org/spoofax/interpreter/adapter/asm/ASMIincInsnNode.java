/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.IincInsnNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMIincInsnNode extends AbstractASMNode {

	private static final long serialVersionUID = 1636485380064565256L;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("IincInsnNode", 3);
	private final IincInsnNode wrappee;
	
	ASMIincInsnNode(IincInsnNode wrappee) {
		super(CTOR);
		this.wrappee = wrappee;
	}

	@Override
	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrapOpcode(wrappee.getOpcode());
		case 1:
			return ASMFactory.wrap(wrappee.incr);
		case 2:
			return ASMFactory.wrap(wrappee.var);
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

}

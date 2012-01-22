/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.IntInsnNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class WrappedIntInsnNode extends WrappedASMNode {

	private static final long serialVersionUID = 7779279187249673594L;
	
	private final IntInsnNode wrappee;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("IntInsnNode", 2);

	WrappedIntInsnNode(IntInsnNode node) {
		super(CTOR);
		this.wrappee = node;
	}

	public IStrategoTerm getSubterm(int index) {
		switch (index) {
		case 0:
			return ASMFactory.wrapOpcode(wrappee.getOpcode());
		case 1:
			return ASMFactory.wrap(wrappee.operand);
		}
		throw new ArrayIndexOutOfBoundsException();
	}

}

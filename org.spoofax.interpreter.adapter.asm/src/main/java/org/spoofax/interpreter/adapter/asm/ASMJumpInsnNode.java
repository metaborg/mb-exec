/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.JumpInsnNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMJumpInsnNode extends WrappedASMNode {

	private static final long serialVersionUID = -3015972936163184990L;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("JumpInsnNode", 1);
	private final JumpInsnNode wrappee;

	public ASMJumpInsnNode(JumpInsnNode wrappee) {
		super(CTOR);
		this.wrappee = wrappee;
	}
	
	@Override
	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrapOpcode(wrappee.getOpcode());
		case 1:
			return ASMFactory.wrap(wrappee.label);
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

}

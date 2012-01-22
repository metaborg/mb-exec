/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.LdcInsnNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class WrappedLdcInsnNode extends WrappedASMNode {

	private static final long serialVersionUID = -3396823075632694750L;
	
	private final LdcInsnNode wrappee;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("LdcInsnNode", 2);

	public WrappedLdcInsnNode(LdcInsnNode node) {
		super(CTOR);
		this.wrappee = node;
	}

	public IStrategoTerm getSubterm(int index) {
		switch (index) {
		case 0:
			return ASMFactory.wrapOpcode(wrappee.getOpcode());
		case 1:
			return ASMFactory.genericWrap(wrappee.cst);
		}
		throw new ArrayIndexOutOfBoundsException();
	}

}

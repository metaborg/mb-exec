/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.MultiANewArrayInsnNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMMultiANewArrayInsnNode extends AbstractASMNode {
	
	private static final long serialVersionUID = 1187976586799465094L;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("MultiANewArrayInsnNode", 3);
	private final MultiANewArrayInsnNode wrappee;

	ASMMultiANewArrayInsnNode(MultiANewArrayInsnNode wrappee) {
		super(CTOR);
		this.wrappee = wrappee;
	}
	
	@Override
	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrapOpcode(wrappee.getOpcode());
		case 1:
			return ASMFactory.wrap(wrappee.desc);
		case 2:
			return ASMFactory.wrap(wrappee.dims);
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

}

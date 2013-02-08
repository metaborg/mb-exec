/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.MethodInsnNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMMethodInsnNode extends AbstractASMNode {
	
	private static final long serialVersionUID = 2437642831650759672L;
	private final static IStrategoConstructor CTOR = new StrategoConstructor("MethodInsnNode", 4); 
	private final MethodInsnNode wrappee;	
	
	ASMMethodInsnNode(MethodInsnNode node) {
		super(CTOR);
		this.wrappee = node;
	}

	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrapOpcode(wrappee.getOpcode());
		case 1:
			return ASMFactory.wrap(wrappee.owner);
		case 2:
			return ASMFactory.wrap(wrappee.name);
		case 3:
			return ASMFactory.wrap(wrappee.desc);
		}
		throw new ArrayIndexOutOfBoundsException();
	}

}

/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.FieldInsnNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class WrappedFieldInsnNode extends WrappedASMNode {

	private static final long serialVersionUID = -924850942458159851L;
	
	private final FieldInsnNode wrappee;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("FieldInsnNode", 2);

	WrappedFieldInsnNode(FieldInsnNode node) {
		super(CTOR);
		this.wrappee = node;
	}

	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrap(wrappee.desc);
		case 1:
			return ASMFactory.wrap(wrappee.name);
		case 2:
			return ASMFactory.wrap(wrappee.owner);
		case 3:
			return ASMFactory.wrap(wrappee.getOpcode());
		case 4:
			return ASMFactory.wrap(wrappee.getType());
		}
		throw new ArrayIndexOutOfBoundsException();
	}

}

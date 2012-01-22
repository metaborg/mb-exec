/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.LookupSwitchInsnNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMLookupSwitchInsnNode extends AbstractASMNode {

	private static final long serialVersionUID = -6356822206354014706L;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("LookupSwitchInsnNode", 4);
	private final LookupSwitchInsnNode wrappee;

	ASMLookupSwitchInsnNode(LookupSwitchInsnNode wrappee) {
		super(CTOR);
		this.wrappee = wrappee;
	}
	
	@Override
	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrapOpcode(wrappee.getOpcode());
		case 1:
			return ASMFactory.wrap(wrappee.dflt);
		case 2:
			return ASMFactory.wrap(wrappee.keys);
		case 3:
			return ASMFactory.wrap(wrappee.labels);
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

}

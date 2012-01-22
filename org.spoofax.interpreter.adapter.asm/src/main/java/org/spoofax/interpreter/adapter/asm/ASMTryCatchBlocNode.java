/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.TryCatchBlockNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMTryCatchBlocNode extends WrappedASMNode {

	private static final long serialVersionUID = 8068162251532568248L;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("TryCatchBlockNode", 3);
	private final TryCatchBlockNode wrappee;

	ASMTryCatchBlocNode(TryCatchBlockNode wrappee) {
		super(CTOR);
		this.wrappee = wrappee;
	}
	
	@Override
	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrap(wrappee.end);
		case 1:
			return ASMFactory.wrap(wrappee.handler);
		case 2:
			return ASMFactory.wrap(wrappee.start);
		case 3:
			return ASMFactory.wrap(wrappee.type);
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

}

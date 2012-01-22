/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.FieldNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMFieldNode extends WrappedASMNode {

	private static final long serialVersionUID = 9012277892471498939L;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("FieldNode", 8);
	private final FieldNode wrappee;

	ASMFieldNode(FieldNode wrappee) {
		super(CTOR);
		this.wrappee = wrappee;
	}
	
	@Override
	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrapAccessFlags(wrappee.access);
		case 1:
			return ASMFactory.wrap(wrappee.attrs);
		case 2:
			return ASMFactory.wrap(wrappee.desc);
		case 3:
			return ASMFactory.wrap(wrappee.invisibleAnnotations);
		case 4:
			return ASMFactory.wrap(wrappee.name);
		case 5:
			return ASMFactory.wrap(wrappee.signature);
		case 6:
			return ASMFactory.wrap(wrappee.visibleAnnotations);
		case 7:
			return ASMFactory.genericWrap(wrappee.value);
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

}

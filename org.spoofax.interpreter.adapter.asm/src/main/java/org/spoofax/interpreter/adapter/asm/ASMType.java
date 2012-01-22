/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.Type;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMType extends WrappedASMNode {

	private static final long serialVersionUID = -1989838540907718491L;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("Type", 6);
	private final Type wrappee;

	ASMType(Type wrappee) {
		super(CTOR);
		this.wrappee = wrappee;
	}
	
	@Override
	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrap(wrappee.getClassName());
		case 1:
			return ASMFactory.wrap(wrappee.getDescriptor());
		case 2:
			return ASMFactory.wrap(wrappee.getInternalName());
		case 3:
			return ASMFactory.wrap(wrappee.getDimensions());
		case 4:
			return ASMFactory.wrap(wrappee.getSize());
		case 5:
			return ASMFactory.wrap(wrappee.getSort());
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

}

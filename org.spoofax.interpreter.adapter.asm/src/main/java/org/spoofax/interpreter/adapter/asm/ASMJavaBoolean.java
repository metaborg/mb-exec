/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMJavaBoolean extends AbstractASMNode {

	private static final long serialVersionUID = 2625229181042123567L;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("Boolean", 1);
	private final IStrategoTerm value;

	ASMJavaBoolean(ASMBooleanValue value) {
		super(CTOR);
		this.value = value;
	}
	@Override
	public IStrategoTerm getSubterm(int index) {
		if(index == 0)
			return value;
		throw new ArrayIndexOutOfBoundsException(index);
	}

}

/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.LocalVariableNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class WrappedLocalVariable extends WrappedASMNode {

	private static final long serialVersionUID = 5082686857335473278L;
	
	private LocalVariableNode wrappee;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("LocalVariable", 6); 
	
	public WrappedLocalVariable(LocalVariableNode node) {
		super(CTOR);
		this.wrappee = node;
	}

	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrap(wrappee.desc);
		case 1:
			return ASMFactory.wrap(wrappee.index);
		case 2:
			return ASMFactory.wrap(wrappee.name);
		case 3: 
			return ASMFactory.wrap(wrappee.signature);
		case 4: 
			return ASMFactory.wrap(wrappee.start);
		case 5: 
			return ASMFactory.wrap(wrappee.end);
		}
		throw new ArrayIndexOutOfBoundsException();
	}


}

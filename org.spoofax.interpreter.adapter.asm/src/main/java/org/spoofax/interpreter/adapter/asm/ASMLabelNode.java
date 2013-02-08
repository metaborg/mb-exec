/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.LabelNode;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMLabelNode extends AbstractASMNode {

	private static final long serialVersionUID = -5744110840726435288L;
	
	private final LabelNode wrappee;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("LabelNode", 1);
	
	ASMLabelNode(LabelNode node) {
		super(CTOR);
		this.wrappee = node;
	}

	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrap(wrappee.getLabel());
		}
		throw new NotImplementedException();
	}

}

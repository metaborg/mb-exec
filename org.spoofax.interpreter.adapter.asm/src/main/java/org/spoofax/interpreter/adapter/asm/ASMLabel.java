/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.Label;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMLabel extends AbstractASMNode {

	private static final long serialVersionUID = -2061512969617267941L;
	
	private final Label wrappee;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("Label", 1);

	ASMLabel(Label node) {
		super(CTOR);
		this.wrappee = node;
	}

	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			try {
				return ASMFactory.wrap(wrappee.getOffset());
			} catch(IllegalStateException e) {
				return None.INSTANCE;
			}
		}
		throw new ArrayIndexOutOfBoundsException();
	}

	public Label getWrappee() {
		return wrappee;
	}
}

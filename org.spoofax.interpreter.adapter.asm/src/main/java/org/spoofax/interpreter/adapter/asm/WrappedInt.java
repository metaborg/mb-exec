/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.TermFactory;

public class WrappedInt extends StrategoInt {

	private static final long serialVersionUID = 505072984325810240L;

	WrappedInt(int value) {
		super(value, TermFactory.EMPTY_LIST, IStrategoTerm.IMMUTABLE);
	}

}

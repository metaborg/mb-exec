/*
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.java;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoAppl;
import org.spoofax.terms.StrategoConstructor;
import org.spoofax.terms.StrategoInt;

public class GenericWrappedTerm extends StrategoAppl {

	private static final long serialVersionUID = -305297606780539827L;
	
	private final Object wrappee;

	public GenericWrappedTerm(String name, Object object) {
		super(new StrategoConstructor("FileChannel", 1),
				new IStrategoTerm[] { new StrategoInt(
						System.identityHashCode(object),
						IStrategoTerm.IMMUTABLE) }, null,
				IStrategoTerm.IMMUTABLE);
		this.wrappee = object;
	}

	public Object getWrappee() {
		return wrappee;
	}

	@Override
	protected boolean doSlowMatch(IStrategoTerm second, int commonStorageType) {
		if (second == null)
			return false;
		if (!(second instanceof GenericWrappedTerm))
			return false;
		GenericWrappedTerm other = (GenericWrappedTerm) second;
		if (wrappee == null) {
			if (other.wrappee == null)
				return true;
			return false;
		}
		return wrappee.equals(other.wrappee);
	}
}
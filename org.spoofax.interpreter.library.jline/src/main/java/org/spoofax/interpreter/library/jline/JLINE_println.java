/*
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.jline;

import jline.ANSIBuffer;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class JLINE_println extends AbstractPrimitive {

	public JLINE_println() {
		super("JLINE_println", 0, 0);
	}

	@Override
	public boolean call(final IContext env, Strategy[] svars,
			IStrategoTerm[] tvars) throws InterpreterException {
		ANSIBuffer buf = JLINE_format.makeANSIBuffer(env);
		if(buf == null)
			return false;
		System.out.println(buf);
		return true;
	}
}

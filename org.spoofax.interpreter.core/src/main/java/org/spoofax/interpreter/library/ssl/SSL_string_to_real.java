/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.util.TermUtils;

public class SSL_string_to_real extends AbstractPrimitive {

	protected SSL_string_to_real() {
		super("SSL_string_to_real", 0, 1);
	}

	@Override
	public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs)
			throws InterpreterException {

		if (!TermUtils.isString(targs[0]))
			return false;

		String s = TermUtils.toJavaString(targs[0]);

		String s0 = s;
		try {
			env.setCurrent(env.getFactory().makeReal(Double.parseDouble(s0)));
			return true;
		} catch (NumberFormatException e) {
			// do nothing
		}

		try {
			s0 = s.trim();
			if (s0.length() > 0 && s0.charAt(0) == '+')
				s0 = s0.substring(1);
			env.setCurrent(env.getFactory().makeReal(Double.parseDouble(s0)));
			return true;
		} catch (NumberFormatException e) {
			; // do nothing
		}

		return false;
	}
}

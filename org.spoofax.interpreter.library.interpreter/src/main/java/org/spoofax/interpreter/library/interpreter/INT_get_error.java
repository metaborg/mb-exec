/*
 * Copyright (c) 2012, Tobi Vollebregt
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.interpreter;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class INT_get_error extends InterpreterAbstractPrimitive {

	private static final String NAME = "INT_get_error";

	public INT_get_error(InterpreterLibrary library) {
		super(library, NAME, 0, 1);
	}

	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
			throws InterpreterException {

		if (tvars.length < 1 ||
				!(tvars[0] instanceof SpoofaxInterpreterTerm))
			return false;

		final SpoofaxInterpreterTerm interpreter = (SpoofaxInterpreterTerm) tvars[0];

		if (interpreter.getLastError() == null)
			return false;

		env.setCurrent(env.getFactory().makeString(interpreter.getLastError()));
		return true;
	}

}

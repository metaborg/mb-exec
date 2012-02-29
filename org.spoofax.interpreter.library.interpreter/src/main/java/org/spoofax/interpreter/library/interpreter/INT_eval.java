/*
 * Copyright (c) 2012, Tobi Vollebregt
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.interpreter;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class INT_eval extends InterpreterAbstractPrimitive {

	private final static String NAME = "INT_eval";

	public INT_eval(InterpreterLibrary library) {
		super(library, NAME, 0, 2);
	}

	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) {

		if (tvars.length < 2 ||
				!(tvars[0] instanceof SpoofaxInterpreterTerm) ||
				tvars[1].getTermType() != IStrategoTerm.STRING)
			return false;

		final SpoofaxInterpreterTerm interpreter = (SpoofaxInterpreterTerm) tvars[0];
		final String line = Tools.asJavaString(tvars[1]);

		if (!interpreter.eval(line))
			return false;

		env.setCurrent(interpreter.current());
		return true;
	}

}

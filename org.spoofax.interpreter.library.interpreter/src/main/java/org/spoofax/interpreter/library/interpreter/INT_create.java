/*
 * Copyright (c) 2012, Tobi Vollebregt
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.interpreter;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.library.ssl.SSLLibrary;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class INT_create extends InterpreterAbstractPrimitive {

	private static final String NAME = "INT_create";

	public INT_create(InterpreterLibrary library) {
		super(library, NAME, 0, 0);
	}

	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) {

		final SSLLibrary ssl = (SSLLibrary) env.getOperatorRegistry("SSL");
		final IOAgent ioAgent = ssl.getIOAgent();
		final ITermFactory termFactory = env.getFactory();

		env.setCurrent(new SpoofaxInterpreterTerm(termFactory, ioAgent));

		return true;
	}

}

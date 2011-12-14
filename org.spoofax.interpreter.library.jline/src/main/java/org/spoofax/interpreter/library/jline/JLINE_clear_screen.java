/*
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.jline;

import java.io.IOException;

import jline.ConsoleReader;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.library.java.JFFLibrary;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class JLINE_clear_screen extends AbstractPrimitive {

	public JLINE_clear_screen() {
		super("JLINE_clear_screen", 0, 0);
	}
	
	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
			throws InterpreterException {
		try {
			ConsoleReader consoleReader = JFFLibrary.unwrap(tvars, 0, ConsoleReader.class);
			consoleReader.clearScreen();
		} catch(IOException e) {
			JLINELibrary.reportException(env, e);
			return false;
		}

		return false;
	}

}

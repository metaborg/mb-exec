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
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.library.java.JFFLibrary;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class JLINE_repl extends AbstractPrimitive {

	public JLINE_repl() {
		super("JLINE_repl", 2, 1);
	}
	
	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
			throws InterpreterException {
		try {
			ConsoleReader consoleReader = JFFLibrary.unwrap(tvars, 0, ConsoleReader.class);
			if(consoleReader == null)
				return false;
			final Strategy promptStrategy = svars[0];
			final Strategy evalStrategy = svars[1];
			final ITermFactory factory = env.getFactory();
			do {
				IStrategoTerm cur = env.current();
				if(!promptStrategy.evaluate(env))
					return false;
				IStrategoTerm prompt = env.current();
				if(prompt.getSubtermCount() == 2 && 
						prompt.getSubterm(0).getTermType() == IStrategoTerm.STRING) {
					cur = Tools.termAt(prompt, 1);
					prompt = Tools.stringAt(prompt, 0);
				} else if (prompt.getTermType() != IStrategoTerm.STRING) { 
					return false;
				}
				final String line = consoleReader.readLine(((IStrategoString) prompt).stringValue());
				env.setCurrent(factory.makeTuple(factory.makeString(line), cur));
				if(!evalStrategy.evaluate(env))
					return false;
			} while(true);
		} catch(IOException e) {
			return JFFLibrary.invokeExceptionHandler(env, e);
		}
	}
}

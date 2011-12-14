/*
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.jline;

import java.io.IOException;
import java.util.List;

import jline.Completor;
import jline.ConsoleReader;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.library.java.GenericWrappedTerm;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class JLINE_make_console extends AbstractPrimitive {

	public JLINE_make_console() {
		super("JLINE_make_console", 1, 0);
	}

	@Override
	public boolean call(final IContext env, Strategy[] svars,
			IStrategoTerm[] tvars) throws InterpreterException {
		try {
			final Strategy completer = svars[0];
			ConsoleReader console = new ConsoleReader();
			console.addCompletor(new Completor() {

				@SuppressWarnings("unchecked")
				@Override
				public int complete(String line, int cursorPos,
						@SuppressWarnings("rawtypes") List suggestions) {
					IStrategoTerm old = env.current();
					try {
						ITermFactory f = env.getFactory();
						env.setCurrent(f.makeTuple(f.makeString(line), f.makeInt(cursorPos)));
						try {
							completer.evaluate(env);
						} catch (InterpreterException e) {
							return -1;
						}
						IStrategoTerm r = env.current();
						if (r.getTermType() == IStrategoTerm.TUPLE
								&& r.getSubtermCount() == 2
								&& r.getSubterm(0).getTermType() == IStrategoTerm.INT
								&& r.getSubterm(1).getTermType() == IStrategoTerm.LIST) {
							IStrategoList list = (IStrategoList) r.getSubterm(1);
							for (IStrategoTerm t : list.getAllSubterms()) {
								if (t.getTermType() == IStrategoTerm.STRING)
									suggestions.add(((IStrategoString) t).stringValue());
							}
							return ((IStrategoInt) r.getSubterm(0)).intValue();
						}
						return -1;
					} finally {
						env.setCurrent(old);
					}
				}
			});
			env.setCurrent(new GenericWrappedTerm("ConsoleReader", console));
		} catch (IOException e) {
			JLINELibrary.reportException(env, e);
			return false;
		}

		return false;
	}

}

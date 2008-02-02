/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import java.io.IOException;

import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ImportTerm extends Strategy {

    private final String path;
    
	public ImportTerm(String path) {
    	this.path = path;
	}

	public IConstruct eval(IContext env) throws InterpreterException {

		try {
			final IStrategoTerm t = env.getFactory().parseFromFile(path);
			env.setCurrent(t);
		} catch (IOException e) {
			throw new InterpreterException(e);
		}
		
		return getHook().pop().onSuccess(env);
    }

	public void prettyPrint(StupidFormatter sf) {
        sf.first("ImportTerm(\"");
        sf.append(path);
        sf.line("\")");
    }
}

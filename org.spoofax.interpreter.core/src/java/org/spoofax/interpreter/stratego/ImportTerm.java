/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import java.io.IOException;
import java.io.InputStream;

import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.library.ssl.SSLLibrary;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ImportTerm extends Strategy {

    private final String path;
    
	public ImportTerm(String path) {
    	this.path = path;
	}

	public IConstruct eval(IContext env) throws InterpreterException {
		try {
		    SSLLibrary op = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
		    IOAgent io = op.getIOAgent();
		    
		    InputStream input = io.openInputStream(path, true);
			IStrategoTerm result = env.getFactory().parseFromStream(input);
			
			env.setCurrent(result);
		} catch (IOException e) {
			throw new InterpreterException("import-term failed", e);
		}
		
		return getHook().pop().onSuccess(env);
    }

	public void prettyPrint(StupidFormatter sf) {
        sf.first("ImportTerm(\"");
        sf.append(path);
        sf.line("\")");
    }
}

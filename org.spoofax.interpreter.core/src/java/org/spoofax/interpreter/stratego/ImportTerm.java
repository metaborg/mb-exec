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
import org.spoofax.terms.io.binary.TermReader;

public class ImportTerm extends Strategy {

    private final String path;
    
    private IStrategoTerm result;
    
	public ImportTerm(String path) {
    	this.path = path;
	}

	public IConstruct eval(IContext env) throws InterpreterException {
        if (result == null) {
            InputStream input = null;
            try {
                SSLLibrary op = (SSLLibrary) env
                        .getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
                IOAgent io = op.getIOAgent();

                input = io.openInputStream(path, true);
                result = new TermReader(env.getFactory()).parseFromStream(input);
            } catch (IOException e) {
                throw new InterpreterException("import-term failed for " + path, e);
            } catch (RuntimeException e) {
                throw new InterpreterException("import-term failed for " + path, e);
            } finally {
                try {
                    if (input != null) input.close();
                } catch (IOException e) {
                    // Silly checked exceptions
                }
            }
        }

        env.setCurrent(result);
		return getHook().pop().onSuccess(env);
    }

	public void prettyPrint(StupidFormatter sf) {
        sf.first("ImportTerm(\"");
        sf.append(path);
        sf.line("\")");
    }
}

/*
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk at strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter;

import java.io.IOException;

import org.spoofax.interpreter.core.Interpreter;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.jsglr.client.InvalidParseTableException;

public class ConcreteInterpreter extends Interpreter {

	public ConcreteInterpreter() throws IOException, InterpreterException, InvalidParseTableException
	{
	}
	
    public void loadConcrete(String file, String[] path, boolean lib) throws IOException, InterpreterException {
    }
}
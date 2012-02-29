/*
 * Copyright (c) 2012, Tobi Vollebregt
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.interpreter;

import org.spoofax.interpreter.library.AbstractStrategoOperatorRegistry;

/**
 * Library that adds the interpreter to the interpreter
 * so you can interpret while you interpret.
 *
 * @author Tobi Vollebregt
 */
public class InterpreterLibrary extends AbstractStrategoOperatorRegistry {

	public static final String REGISTRY_NAME = "INT";

	public InterpreterLibrary() {
		add(new INT_create(this));
		add(new INT_eval(this));
		add(new INT_get_error(this));
	}

	public String getOperatorRegistryName() {
		return REGISTRY_NAME;
	}

}

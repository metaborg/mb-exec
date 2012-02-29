/*
 * Copyright (c) 2012, Tobi Vollebregt
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.interpreter;

import org.spoofax.interpreter.library.AbstractPrimitive;

/**
 * @author Tobi Vollebregt
 */
public abstract class InterpreterAbstractPrimitive extends AbstractPrimitive {

	protected final InterpreterLibrary library;

	public InterpreterAbstractPrimitive(InterpreterLibrary library, String name, int svars, int tvars) {
		super(name, svars, tvars);
		this.library = library;
	}

}

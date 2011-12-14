/*
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.jline;

import java.io.IOException;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractStrategoOperatorRegistry;

public class JLINELibrary extends AbstractStrategoOperatorRegistry {

	public static final String REGISTRY_NAME = "JLINE";

	public JLINELibrary() {
		add(new JLINE_make_console());
	}
	
	@Override
	public String getOperatorRegistryName() {
		return REGISTRY_NAME;
	}

	public static void reportException(IContext env, IOException e) {
		// TODO Auto-generated method stub
		
	}

}

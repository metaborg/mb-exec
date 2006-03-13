/*
 * Created on Jul 22, 2004
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.ast.loaders;

import org.spoofax.Config;

/**
 * @author Karl Trygve Kalleberg <karltk@ii.uib.no>
 *
 * Part of spoofax
 */
public class CPPLoader extends PipedCommandLoader {
	
	public CPPLoader() {
		super(new String[] { "cpp", "cxx", "c++", "C" }, Config.CPP2AST_BIN);
	}
	
	protected String getLanguage() { return "cpp"; }
}

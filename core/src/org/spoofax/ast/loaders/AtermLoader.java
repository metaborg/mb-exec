/*
 * Created on Jul 22, 2004
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.ast.loaders;

import java.net.URL;

import org.spoofax.ast.AST;

/**
 * @author Karl Trygve Kalleberg <karltk@ii.uib.no>
 *
 * Part of spoofax
 */
public class AtermLoader extends ILoader {

	/**
	 * 
	 */
	public AtermLoader() {
		super(new String[] { "ast" });
	}
	
	public AST load(URL url) {
		return null;
	}

}

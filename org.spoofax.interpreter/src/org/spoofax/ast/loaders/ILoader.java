/*
 * Created on Jul 21, 2004
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.ast.loaders;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import org.spoofax.Logger;
import org.spoofax.ast.AST;

/**
 * @author Karl Trygve Kalleberg <karltk@ii.uib.no>
 *
 * Part of spoofax
 */
public abstract class ILoader {

	private String[] supportedExtensions;
	
	protected ILoader(String[] exts) {
		supportedExtensions = exts;
	}
	public Collection getExtensions() { 
		Logger.log("getExtensions()" + supportedExtensions);
		return Arrays.asList(supportedExtensions); }
	
	public abstract AST load(URL url);
}

/*
 * Created on Jul 21, 2004
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.ast;

import java.io.PrintStream;
import java.net.URL;

import aterm.ATerm;

/**
 * @author Karl Trygve Kalleberg <karltk@ii.uib.no>
 *
 * Part of spoofax
 */
public class AST {
	private ATerm aterm;
	private String language;
	private URL resource;
	
	public AST(ATerm aterm, String language, URL resource) {
		this.aterm = aterm;
		this.language = language;
		this.resource = resource;
	}
	
	public void dump(PrintStream out) {
		out.println(aterm);
	}
	
	public URL getResource() { return resource; }
	public String getLanguage() { return language; }
}

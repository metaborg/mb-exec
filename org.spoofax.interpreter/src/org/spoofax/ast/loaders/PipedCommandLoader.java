/*
 * Created on Jul 21, 2004
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.ast.loaders;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.spoofax.Logger;
import org.spoofax.ast.AST;

import aterm.ATerm;
import aterm.pure.PureFactory;

/**
 * @author Karl Trygve Kalleberg <karltk@ii.uib.no>
 *
 * Part of spoofax
 */
public abstract class PipedCommandLoader extends ILoader {

	private String converterCommand;
	
	public AST load(URL url) {
		Logger.log("Loading " + url);
		String[] args = new String[2];
		args[0] = converterCommand;
		args[1] = url.getPath();
		
		Runtime runtime = Runtime.getRuntime();
		Process child = null;
		try {
			child = runtime.exec(args);
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
		InputStream ins = child.getInputStream();
		PureFactory factory = new PureFactory();
		try {
			ATerm term = factory.readFromFile(ins);
			return new AST(term, getLanguage(), url);
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	abstract protected String getLanguage();
	
	protected void setConverterCommand(String converterCommand) {
		this.converterCommand = converterCommand;
	}
	
	protected PipedCommandLoader(String[] exts, String converterCommand) {
		super(exts);
		this.converterCommand = converterCommand;
		Logger.log("PipedCommandLoader(String[], String)");
	}
}

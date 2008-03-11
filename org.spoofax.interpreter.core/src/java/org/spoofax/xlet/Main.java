/*
 * Created on Jul 21, 2004
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.xlet;

import java.io.IOException;

import org.spoofax.interpreter.core.InterpreterException;

/**
 * @author Karl Trygve Kalleberg <karltk near strategoxt.org>
 *
 * Part of spoofax
 */
public class Main {

	public static void main(String[] args) throws IOException, InterpreterException {
	    if(args.length < 3) {
	        System.err.println("Usage: <action> <project-path> <file>");
	        System.exit(2);
        }

	    String libPath = System.getProperty("spoofax.lib.path");
        String xletPath = System.getProperty("spoofax.lib.path");
        
        Instance ist = new Instance(libPath, xletPath);
        
        ist.loadXlet(args[0]);
        
        
    }
}

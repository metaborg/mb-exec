/*
 * Created on Jul 21, 2004
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.xlet;

import java.io.IOException;
import java.util.Vector;

import org.spoofax.ast.AST;
import org.spoofax.ast.ASTFactory;
import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.Interpreter;

/**
 * @author Karl Trygve Kalleberg <karltk@ii.uib.no>
 *
 * Part of spoofax
 */
public class Main {

	public static void main(String[] args) throws IOException, FatalError {
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

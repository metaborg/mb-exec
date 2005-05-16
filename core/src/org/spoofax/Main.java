/*
 * Created on Jul 21, 2004
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax;

import java.util.Vector;

import org.spoofax.ast.AST;
import org.spoofax.ast.ASTFactory;

/**
 * @author Karl Trygve Kalleberg <karltk@ii.uib.no>
 *
 * Part of spoofax
 */
public class Main {

	public static void main(String[] args) {
		Vector vec = new Vector();
		
		ASTFactory factory = new ASTFactory();
		
		for(int i=0;i<args.length;i++)
			if(args[i].equals("--parse"))
				vec.add(args[i + 1]);

		for(int i=0;i<vec.size();i++) {
			Logger.log("Loading " + vec.elementAt(i) + "...");
			AST ast = null;
			try {
				ast = factory.loadFromFile((String)vec.elementAt(i));
			} catch(Exception e) {
				e.printStackTrace();
			}
			ast.dump(System.out);
		}
	}
}

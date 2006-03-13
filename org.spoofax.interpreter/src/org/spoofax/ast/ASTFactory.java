/*
 * Created on Jul 19, 2004
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.ast;

import java.net.MalformedURLException;
import java.net.URL;

import org.spoofax.ast.loaders.ILoader;
import org.spoofax.ast.loaders.LoaderFactory;

/**
 * @author Karl Trygve Kalleberg <karltk@ii.uib.no>
 *
 * Part of spoofax
 */
public class ASTFactory {

		private LoaderFactory loaderFactory;
		
		public ASTFactory() {
			loaderFactory = LoaderFactory.getInstance();
		}
		
		public AST loadFromFile(String path) throws Exception {

			String ext = getFileExtension(path);
			ILoader loader = loaderFactory.findLoader(ext);  

			try {
				return loader.load(new URL("file://" + path));
			} catch(MalformedURLException e) {
				throw new Exception("Failed to load");
			}
		}

		/**
		 * @param path - The path
		 * @return The filename's extension, if any
		 */
		private String getFileExtension(String path) {
			String[] parts = path.split("\\.");
			return parts[parts.length - 1];
		}
}

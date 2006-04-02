/*
 * Created on Jul 21, 2004
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.ast.loaders;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;

import org.spoofax.Logger;

/**
 * @author Karl Trygve Kalleberg <karltk@ii.uib.no>
 *
 * Part of spoofax
 */
public class LoaderFactory {
	
	static LoaderFactory self;
	
	private Collection<ILoader> loaders;

	private LoaderFactory() {
		Logger.log("Constructing LoaderFactory");
		loaders = new ArrayList<ILoader>();

		loaders.add(new CLoader());
		loaders.add(new CPPLoader());
		loaders.add(new JavaLoader());
		loaders.add(new AtermLoader());
	}

	public static LoaderFactory getInstance() { 
		if(self == null)
			self = new LoaderFactory();
		
		return self;
	}
	
	public ILoader findLoader(String ext) {
		Iterator iter = loaders.iterator();
		while(iter.hasNext()) { 
			ILoader l = (ILoader)iter.next();
			Logger.log("" + l);
			if(l.getExtensions().contains(ext))
				return l;
		}
		return null;
		
	}

	public boolean isExtensionSupported(String ext) {
		return findLoader(ext) != null;
	}
	
	public ILoader create(String extension) {
		ILoader l = findLoader(extension);
		return l;
	}
}

/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.JavaCore;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_create_package extends ECJPrimitive {

    public ECJ_create_package() {
        super("ECJ_create_package", 0, 2);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

        if (!Tools.isTermString(tvars[0]))
            return false;
        if (!Tools.isTermString(tvars[1]))
            return false;

        final String packageName = Tools.asJavaString(tvars[1]);
        final String baseName = Tools.asJavaString(tvars[0]);
        IProject project = getLibrary(env).getCurrentProject();
        IContainer container = baseName.length() == 0 ? project : project.getFolder(baseName);
        
        try {
        	if(!packageName.equals("")) {
        		String[] xs = packageName.split("\\.");
        		for(int i = 0; i < xs.length; i++) {
        			container = container.getFolder(new Path(xs[i]));	
        			if(!container.exists()) {
        				((IFolder)container).create(true, true, getLibrary(env).getNullProgressMonitor());
        			}
        		}
        		env.setCurrent(ECJFactory.wrap(JavaCore.create(container)));
        	} 
        } catch(CoreException e) {
        	e.printStackTrace();
        	return false;
        }
        return true;
    }

}

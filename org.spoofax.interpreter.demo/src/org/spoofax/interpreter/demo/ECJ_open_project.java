/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.demo;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.adapters.ecj.ECJFactory;
import org.spoofax.interpreter.library.Primitive;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_open_project extends Primitive {

    public ECJ_open_project() {
        super("ECJ_open_project", 0, 1);
    }
    @Override
    public boolean call(IContext env, List<IConstruct> svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!Tools.isTermString(tvars[0]))
            return false;
        
        String name = Tools.javaString(tvars[0]);
        
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();
        IProject proj = root.getProject(name);
        if(proj == null)
            return false;
        env.setCurrent(ECJFactory.wrap(proj));
        return true;
    }

}

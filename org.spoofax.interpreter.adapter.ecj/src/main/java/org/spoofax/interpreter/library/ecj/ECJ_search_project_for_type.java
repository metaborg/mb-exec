/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_search_project_for_type extends AbstractPrimitive {

    public ECJ_search_project_for_type() {
        super("ECJ_search_project_for_type", 0, 3);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!ECJTools.isIJavaProject(tvars[0]))
            return false;
        if(!Tools.isTermString(tvars[1]))
            return false;
        if(!Tools.isTermInt(tvars[2]))
        	return false;
        
        final boolean checkDeeply = Tools.asJavaInt(tvars[2]) != 0 ? true : false;
        ECJLibrary ecj = (ECJLibrary)env.getOperatorRegistry(ECJLibrary.REGISTRY_NAME);
        IJavaProject proj = ECJTools.asIJavaProject(tvars[0]);
        
        try {
            IType t = null;
            if(checkDeeply)
            	t = proj.findType(Tools.asJavaString(tvars[1]), ecj.getNullProgressMonitor());
            else
            	t = proj.findType(Tools.asJavaString(tvars[1]));
            if(t == null)
                return false;
            env.setCurrent(ECJFactory.wrap(t));
        } catch(JavaModelException e) {
            return false;
        }
        
        return true;
    }

}

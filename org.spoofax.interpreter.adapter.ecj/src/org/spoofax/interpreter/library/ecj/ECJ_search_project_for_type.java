/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_search_project_for_type extends AbstractPrimitive {

    public ECJ_search_project_for_type() {
        super("ECJ_search_project_for_type", 0, 2);
    }
    
    @Override
    public boolean call(IContext env, IConstruct[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!ECJTools.isIJavaProject(tvars[0]))
            return false;
        if(!Tools.isTermString(tvars[1]))
            return false;
        
        IJavaProject proj = ECJTools.asIJavaProject(tvars[0]);
        
        try {
            IType t = proj.findType(Tools.asJavaString(tvars[1]));
            env.setCurrent(ECJFactory.wrap(t));
        } catch(JavaModelException e) {
            return false;
        }
        
        return true;
    }

}

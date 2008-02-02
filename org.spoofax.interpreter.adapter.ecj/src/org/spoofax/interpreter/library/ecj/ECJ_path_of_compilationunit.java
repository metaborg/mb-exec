/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_path_of_compilationunit extends ECJPrimitive {

    public ECJ_path_of_compilationunit() {
        super("ECJ_path_of_compilationunit", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!ECJTools.isCompilationUnit(tvars[0]))
            return false;
        
        CompilationUnit n = ECJTools.asCompilationUnit(tvars[0]);
        
        IJavaElement je = n.getJavaElement();
        if(je == null)
            return false;
        
        try {
            env.setCurrent(ECJFactory.wrap(je.getCorrespondingResource().getProjectRelativePath().toString()));
        } catch (JavaModelException e) {
            return false;
        }
        return true;
    }

}

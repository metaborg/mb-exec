/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.library;

import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.spoofax.ecjadapter.adapter.ECJFactory;
import org.spoofax.ecjadapter.adapter.WrappedASTNode;
import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_method_of_methoddecl extends AbstractPrimitive {

    public ECJ_method_of_methoddecl() {
        super("ECJ_method_of_methoddecl", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, IConstruct[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!(tvars[0] instanceof WrappedASTNode))
            return false;
        
        WrappedASTNode n = (WrappedASTNode) tvars[0];
        if(!(n.getWrappee() instanceof MethodDeclaration))
            return false;
        
        MethodDeclaration m = (MethodDeclaration) n.getWrappee();
        
        IMethodBinding mb = m.resolveBinding();
        if(mb == null)
            return false;
        
        env.setCurrent(ECJFactory.wrap(mb));
        return true;
    }

}

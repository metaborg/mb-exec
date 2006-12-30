/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.library;

import java.util.List;

import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.ecjadapter.adapter.ECJFactory;
import org.spoofax.ecjadapter.adapter.WrappedASTNode;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_type_of_typedecl extends AbstractPrimitive {

    public ECJ_type_of_typedecl() {
        super("ECJ_type_of_typedecl", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, List<IConstruct> svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!(tvars[0] instanceof WrappedASTNode))
            return false;
        
        WrappedASTNode n = (WrappedASTNode) tvars[0];
        if(!(n.getWrappee() instanceof AbstractTypeDeclaration))
            return false;
        
        AbstractTypeDeclaration t = (AbstractTypeDeclaration) n.getWrappee();
        
        ITypeBinding tb = t.resolveBinding();
        if(tb == null)
            return false;
        
        env.setCurrent(ECJFactory.wrap(tb));
        return true;
    }

}

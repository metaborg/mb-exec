/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.library;

import java.util.List;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.ecjadapter.adapter.ECJFactory;
import org.spoofax.ecjadapter.adapter.WrappedASTNode;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_type_of_expr extends AbstractPrimitive {

    public ECJ_type_of_expr() {
        super("ECJ_type_of_expr", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, List<IConstruct> svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!(tvars[0] instanceof WrappedASTNode))
            return false;
        
        WrappedASTNode n = (WrappedASTNode) tvars[0];
        if(!(n.getWrappee() instanceof Expression))
            return false;
        
        Expression e = (Expression) n.getWrappee();
        
        ITypeBinding tb = e.resolveTypeBinding();
        if(tb == null)
            return false;
        
        env.setCurrent(ECJFactory.wrap(tb));
        return true;
    }

}

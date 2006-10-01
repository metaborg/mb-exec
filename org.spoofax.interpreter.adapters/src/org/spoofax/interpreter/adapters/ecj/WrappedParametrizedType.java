/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedParametrizedType extends WrappedAppl {

    private final ParameterizedType wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("ParameterizedType", 2);
    
    
    WrappedParametrizedType(ParameterizedType wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return WrappedECJFactory.wrapType(wrappee.getType());
        case 1:
            return WrappedECJFactory.wrap(wrappee.typeArguments());
        }
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ASTNode getWrappee() {
        return wrappee;
    }

    public IStrategoTerm[] getArguments() {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

}

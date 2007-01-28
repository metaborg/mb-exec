/*
 * Created on 1. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.MethodInvocation;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedMethodInvocation extends WrappedExpression {

    private final MethodInvocation wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("MethodInvocation", 4);
    
    WrappedMethodInvocation(MethodInvocation wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrapExpression(wrappee.getExpression());
        case 1:
            return ECJFactory.wrap(wrappee.getName());
        case 2:
            return ECJFactory.wrap(wrappee.typeArguments());
        case 3:
            return ECJFactory.wrap(wrappee.arguments());
        }
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public MethodInvocation getWrappee() {
        return wrappee;
    }
}

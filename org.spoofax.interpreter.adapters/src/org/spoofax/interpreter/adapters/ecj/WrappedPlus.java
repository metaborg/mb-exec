/*
 * Created on 1. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedPlus extends WrappedAppl {

    private final InfixExpression wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("Plus", 2); 
        
    WrappedPlus(InfixExpression wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
        
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrapExpression(wrappee.getLeftOperand());
        case 1:
            return ECJFactory.wrapExpression(wrappee.getRightOperand());
        }
        throw new NotImplementedException();
    }

    @Override
    public ASTNode getWrappee() {
        return wrappee;
    }
}

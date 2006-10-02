/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedInfixExpression extends WrappedAppl {

    private final InfixExpression wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("InfixExpression", 3);
    
    WrappedInfixExpression(InfixExpression wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            // FIXME should probably convert to Plus/Minus/Divide/Times/...
            return WrappedECJFactory.wrap(wrappee.getOperator().toString());
        case 1:
            return WrappedECJFactory.wrapExpression(wrappee.getLeftOperand());
        case 2:
            return WrappedECJFactory.wrapExpression(wrappee.getRightOperand());
            
        }
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ASTNode getWrappee() {
        return wrappee;
    }
}

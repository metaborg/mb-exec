/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.InfixExpression;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedInfixExpression extends WrappedExpression {
    
    private static final long serialVersionUID = 1L;

    private final InfixExpression wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("InfixExpression", 4);
    
    WrappedInfixExpression(InfixExpression wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            // FIXME should probably convert to Plus/Minus/Divide/Times/...
            return ECJFactory.wrap(wrappee.getOperator().toString());
        case 1:
            return ECJFactory.wrapExpression(wrappee.getLeftOperand());
        case 2:
            return ECJFactory.wrapExpression(wrappee.getRightOperand());
        case 3: 
        	return ECJFactory.wrap(wrappee.extendedOperands());
            
        }
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public InfixExpression getWrappee() {
        return wrappee;
    }
}

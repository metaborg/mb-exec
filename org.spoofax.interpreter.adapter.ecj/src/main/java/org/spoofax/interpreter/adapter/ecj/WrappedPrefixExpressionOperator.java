/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression.Operator;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedPrefixExpressionOperator extends AbstractWrappedECJNode {

    private static final long serialVersionUID = 1L;

    private final PrefixExpression.Operator wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("PrefixExpressionOperator", 1);
    
    WrappedPrefixExpressionOperator(PrefixExpression.Operator wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0) {
            // FIXME use constants
            if(wrappee == Operator.COMPLEMENT)
                return ECJFactory.wrap("~");
            if(wrappee == Operator.DECREMENT)
                return ECJFactory.wrap("--");
            if(wrappee == Operator.INCREMENT)
                return ECJFactory.wrap("++");
            if(wrappee == Operator.MINUS)
                return ECJFactory.wrap("-");
            if(wrappee == Operator.NOT)
                return ECJFactory.wrap("!");
            if(wrappee == Operator.PLUS)
                return ECJFactory.wrap("+");
            else
                throw new NotImplementedException();
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public PrefixExpression.Operator getWrappee() {
        return wrappee;
    }

}

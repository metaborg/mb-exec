/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PostfixExpression.Operator;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.util.NotImplementedException;

public class WrappedPostfixExpressionOperator extends AbstractWrappedECJNode {

    private static final long serialVersionUID = 1L;
    
    private final PostfixExpression.Operator wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("PostfixExpressionOperator", 1);
    
    WrappedPostfixExpressionOperator(PostfixExpression.Operator wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0) {
            if(wrappee == Operator.DECREMENT)
                return ECJFactory.wrap("--");
            else if(wrappee == Operator.INCREMENT)
                return ECJFactory.wrap("++");
            else
                throw new NotImplementedException();
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public PostfixExpression.Operator getWrappee() {
        return wrappee;
    }
}

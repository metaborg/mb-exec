/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PostfixExpression.Operator;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedPostfixExpressionOperator extends WrappedECJNode {

    private final PostfixExpression.Operator wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("PostfixExpressionOperator", 1);
    
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

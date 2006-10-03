/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression.Operator;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedPrefixExpressionOperator extends WrappedAppl {

    private final PrefixExpression.Operator wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("PrefixExpressionOperator", 1);
    
    WrappedPrefixExpressionOperator(PrefixExpression.Operator wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0) {
            // FIXME use constants
            if(wrappee == Operator.COMPLEMENT)
                return WrappedECJFactory.wrap("~");
            if(wrappee == Operator.DECREMENT)
                return WrappedECJFactory.wrap("--");
            if(wrappee == Operator.INCREMENT)
                return WrappedECJFactory.wrap("++");
            if(wrappee == Operator.MINUS)
                return WrappedECJFactory.wrap("-");
            if(wrappee == Operator.NOT)
                return WrappedECJFactory.wrap("!");
            if(wrappee == Operator.PLUS)
                return WrappedECJFactory.wrap("+");
            else
                throw new NotImplementedException();
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ASTNode getWrappee() {
        throw new NotImplementedException();
    }

}

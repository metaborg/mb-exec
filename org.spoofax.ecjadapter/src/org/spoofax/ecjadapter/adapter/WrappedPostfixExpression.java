/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.eclipse.jdt.core.dom.PostfixExpression;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedPostfixExpression extends WrappedExpression {

    private final PostfixExpression wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("PostfixExpression", 2);
    
    WrappedPostfixExpression(PostfixExpression wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.getOperator());
        case 1:
            return ECJFactory.wrapExpression(wrappee.getOperand());
        }
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public PostfixExpression getWrappee() {
        return wrappee;
    }
}

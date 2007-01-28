/*
 * Created on 1. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedExpressionStatement extends WrappedStatement {

    private final ExpressionStatement wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("ExpressionStatement", 1);
    
    public WrappedExpressionStatement(ExpressionStatement wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrapExpression(wrappee.getExpression());
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ExpressionStatement getWrappee() {
        return wrappee;
    }
}

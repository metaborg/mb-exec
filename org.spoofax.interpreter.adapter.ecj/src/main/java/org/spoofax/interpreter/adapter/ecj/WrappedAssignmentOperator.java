/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Assignment.Operator;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedAssignmentOperator extends AbstractWrappedECJNode {
    
    private static final long serialVersionUID = 1L;

    private final Assignment.Operator wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("AssignmentOperator", 1);
    
    WrappedAssignmentOperator(Assignment.Operator wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0) {
            if(wrappee == Operator.ASSIGN)
                return ECJFactory.wrap("=");
            else if(wrappee == Operator.PLUS_ASSIGN)
                return ECJFactory.wrap("+=");
            else if(wrappee == Operator.MINUS_ASSIGN)
                return ECJFactory.wrap("-=");
            else if(wrappee == Operator.TIMES_ASSIGN)
                return ECJFactory.wrap("*=");
            else if(wrappee == Operator.DIVIDE_ASSIGN)
                return ECJFactory.wrap("/=");
            else if(wrappee == Operator.BIT_AND_ASSIGN)
                return ECJFactory.wrap("&=");
            else if(wrappee == Operator.BIT_OR_ASSIGN)
                return ECJFactory.wrap("|=");
            else if(wrappee == Operator.BIT_XOR_ASSIGN)
                return ECJFactory.wrap("^=");
            else if(wrappee == Operator.LEFT_SHIFT_ASSIGN)
                return ECJFactory.wrap("<<=");
            else if(wrappee == Operator.REMAINDER_ASSIGN)
                return ECJFactory.wrap("%=");
            else if(wrappee == Operator.RIGHT_SHIFT_SIGNED_ASSIGN)
                return ECJFactory.wrap(">>=");
            else if(wrappee == Operator.RIGHT_SHIFT_UNSIGNED_ASSIGN)
                return ECJFactory.wrap(">>>=");
            else
                throw new NotImplementedException();
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public Assignment.Operator getWrappee() {
        return wrappee;
    }
}

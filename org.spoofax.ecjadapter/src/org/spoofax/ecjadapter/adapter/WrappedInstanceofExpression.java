/*
 * Created on 1. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.eclipse.jdt.core.dom.InstanceofExpression;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedInstanceofExpression extends WrappedExpression {

    private final InstanceofExpression wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("InstanceofExpression", 2);
    
    WrappedInstanceofExpression(InstanceofExpression wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrapExpression(wrappee.getLeftOperand());
        case 1:
            return ECJFactory.wrapType(wrappee.getRightOperand());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public InstanceofExpression getWrappee() {
        return wrappee;
    }
}

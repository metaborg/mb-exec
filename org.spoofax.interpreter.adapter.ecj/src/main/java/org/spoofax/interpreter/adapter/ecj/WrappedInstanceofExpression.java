/*
 * Created on 1. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.InstanceofExpression;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedInstanceofExpression extends WrappedExpression {

    private static final long serialVersionUID = 1L;

    private final InstanceofExpression wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("InstanceofExpression", 2);
    
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

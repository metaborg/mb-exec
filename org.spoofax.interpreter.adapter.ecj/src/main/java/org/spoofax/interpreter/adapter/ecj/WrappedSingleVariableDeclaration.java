/*
 * Created on 1. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedSingleVariableDeclaration extends WrappedVariableDeclaration {

    private static final long serialVersionUID = 1L;
    
    private final SingleVariableDeclaration wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("SingleVariableDeclaration", 5);
    
    WrappedSingleVariableDeclaration(SingleVariableDeclaration wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.modifiers());
        case 1:
            return ECJFactory.wrapType(wrappee.getType());
        case 2:
            return ECJFactory.wrap(wrappee.getName());
        case 3:
            return ECJFactory.wrap(wrappee.getExtraDimensions());
        case 4:
            return ECJFactory.wrapExpression(wrappee.getInitializer());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public SingleVariableDeclaration getWrappee() {
        return wrappee;
    }
}

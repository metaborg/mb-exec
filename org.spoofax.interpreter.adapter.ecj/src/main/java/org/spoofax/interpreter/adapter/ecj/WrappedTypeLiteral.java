/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.TypeLiteral;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedTypeLiteral extends WrappedExpression {

    private static final long serialVersionUID = 1L;

    private final TypeLiteral wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("TypeLiteral", 1);
    
    WrappedTypeLiteral(TypeLiteral wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrapType(wrappee.getType());
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public TypeLiteral getWrappee() {
        return wrappee;
    }

}

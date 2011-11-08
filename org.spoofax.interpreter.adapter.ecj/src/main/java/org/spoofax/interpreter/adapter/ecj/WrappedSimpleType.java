/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.SimpleType;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedSimpleType extends WrappedType {

    private static final long serialVersionUID = 1L;

    private final SimpleType wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("SimpleType", 1);
    
    WrappedSimpleType(SimpleType wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrapName(wrappee.getName());
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public SimpleType getWrappee() {
        return wrappee;
    }
}

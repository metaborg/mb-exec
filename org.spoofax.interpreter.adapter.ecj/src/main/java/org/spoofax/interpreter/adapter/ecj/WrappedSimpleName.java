/*
 * Created on 1. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.SimpleName;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedSimpleName extends WrappedName {

    private static final long serialVersionUID = 1L;

    private final SimpleName wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("SimpleName", 1); 

    WrappedSimpleName(SimpleName wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrap(wrappee.getIdentifier());
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public SimpleName getWrappee() {
        return wrappee;
    }
}

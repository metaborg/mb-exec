/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.Initializer;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedInitializer extends WrappedBodyDeclaration {

    private static final long serialVersionUID = 1L;

    private final Initializer wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("Initializer", 1);
    
    WrappedInitializer(Initializer wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0) 
            return ECJFactory.wrap(wrappee.getBody());
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public Initializer getWrappee() {
        return wrappee;
    }

}

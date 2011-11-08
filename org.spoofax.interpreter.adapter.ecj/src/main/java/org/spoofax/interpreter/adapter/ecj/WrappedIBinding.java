/*
 * Created on 10. mars. 2007
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.IBinding;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedIBinding extends AbstractWrappedBinding {
    
    private static final long serialVersionUID = 1L;
    
    private final IBinding wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("Binding", 3);

    protected WrappedIBinding(IBinding wrappee) {
        super(CTOR);
        this.wrappee = wrappee; 
    }

    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.getKind());
        case 1:
            return ECJFactory.wrap(wrappee.getModifiers());
        case 2:
            return ECJFactory.wrap(wrappee.getName());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public IBinding getWrappee() {
        return wrappee;
    }

}

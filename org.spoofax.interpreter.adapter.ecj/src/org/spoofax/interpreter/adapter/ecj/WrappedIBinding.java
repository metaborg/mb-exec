/*
 * Created on 10. mars. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.IBinding;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedIBinding extends AbstractWrappedBinding {
    
    private static final long serialVersionUID = 1L;
    
    private final IBinding wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("Binding", 3);

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

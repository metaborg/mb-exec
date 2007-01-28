/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.IMethodBinding;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedIMethodBinding extends AbstractWrappedBinding {

    private final IMethodBinding wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("MethodBinding", 4);
    
    WrappedIMethodBinding(IMethodBinding wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    public IMethodBinding getWrappee() {
        return wrappee;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            // FIXME should become a QualifiedName
            return ECJFactory.wrap(wrappee.getDeclaringClass());
        case 1:
            return ECJFactory.wrap(wrappee.getName());
        case 2:
            return ECJFactory.wrap(wrappee.getReturnType());
        case 3:
            return ECJFactory.wrap(wrappee.getParameterTypes());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

}

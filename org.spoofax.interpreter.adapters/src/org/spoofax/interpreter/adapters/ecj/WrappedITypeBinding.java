/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ITypeBinding;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedITypeBinding extends AbstractWrappedBinding {

    private final ITypeBinding wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("TypeBinding", 1);
    
    WrappedITypeBinding(ITypeBinding wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    public ITypeBinding getWrappee() {
        return wrappee;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            // FIXME should become a QualifiedName
            return ECJFactory.wrap(wrappee.getQualifiedName());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

}

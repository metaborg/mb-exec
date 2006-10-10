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
    private final static IStrategoConstructor CTOR = new ASTCtor("TypeBinding", 6);
    
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
            if(wrappee.getPackage() == null)
                return ECJFactory.wrap(new String[0]);
            else 
                return ECJFactory.wrap(wrappee.getPackage().getNameComponents());
        case 1:
            // FIXME should become a QualifiedName
            return ECJFactory.wrap(wrappee.getQualifiedName());
        case 2:
            if(wrappee.getSuperclass() == null)
                return None.INSTANCE;
            else 
                return ECJFactory.wrap(wrappee.getSuperclass().getQualifiedName());
        case 3:
            return ECJFactory.wrap(wrappee.getTypeArguments());
        case 4:
            return ECJFactory.wrap(wrappee.getDimensions());
        case 5:
            return ECJFactory.wrap(wrappee.getElementType());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

}

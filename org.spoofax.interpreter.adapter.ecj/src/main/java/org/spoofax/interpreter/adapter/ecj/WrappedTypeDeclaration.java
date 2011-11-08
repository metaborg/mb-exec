/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedTypeDeclaration extends WrappedAbstractTypeDeclaration {

    private static final long serialVersionUID = 1L;

    private final TypeDeclaration wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("TypeDeclaration", 8); 
    
    protected WrappedTypeDeclaration(TypeDeclaration wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.getJavadoc());
        case 1:
            return ECJFactory.wrap(wrappee.modifiers());
        case 2:
            return ECJFactory.wrap(wrappee.getName());
        case 3:
            return ECJFactory.wrap(wrappee.typeParameters());
        case 4:
            return ECJFactory.wrapType(wrappee.getSuperclassType());
        case 5:
            return ECJFactory.wrap(wrappee.superInterfaceTypes());
        case 6:
            return ECJFactory.wrap(wrappee.bodyDeclarations());
        case 7:
        	return ECJFactory.wrap(wrappee.isInterface() ? 1 : 0);
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public TypeDeclaration getWrappee() {
        return wrappee;
    }

}

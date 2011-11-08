/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedEnumDeclaration extends WrappedAbstractTypeDeclaration {
    
    private static final long serialVersionUID = 1L;

    private final EnumDeclaration wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("EnumDeclaration", 5);
    
    WrappedEnumDeclaration(EnumDeclaration wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.modifiers());
        case 1:
            return ECJFactory.wrap(wrappee.getName());
        case 2:
            return ECJFactory.wrap(wrappee.superInterfaceTypes());
        case 3:
            return ECJFactory.wrap(wrappee.enumConstants());
        case 4:
            return ECJFactory.wrap(wrappee.bodyDeclarations());

        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public EnumDeclaration getWrappee() {
        return wrappee;
    }

}

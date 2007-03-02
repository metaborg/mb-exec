/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedFieldDeclaration extends WrappedBodyDeclaration {

    private final FieldDeclaration wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("FieldDeclaration", 4);
    
    WrappedFieldDeclaration(FieldDeclaration wrappee) {
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
            return ECJFactory.wrapType(wrappee.getType());
        case 3:
            return ECJFactory.wrap(wrappee.fragments());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public FieldDeclaration getWrappee() {
        return wrappee;
    }

}

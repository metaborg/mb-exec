/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedMethodDeclaration extends WrappedBodyDeclaration {

    private final MethodDeclaration wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("MethodDeclaration", 7); 
    
    WrappedMethodDeclaration(MethodDeclaration wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.modifiers());
        case 1:
            return ECJFactory.wrapType(wrappee.getReturnType2());
        case 2:
            return ECJFactory.wrap(wrappee.typeParameters());
        case 3:
            return ECJFactory.wrap(wrappee.getName());
        case 4:
            return ECJFactory.wrap(wrappee.parameters());
        case 5:
            return ECJFactory.wrap(wrappee.thrownExceptions());
        case 6:
            return ECJFactory.wrap(wrappee.getBody());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public MethodDeclaration getWrappee() {
        return wrappee;
    }
}

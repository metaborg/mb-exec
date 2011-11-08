/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedClassInstanceCreation extends WrappedExpression {
    
    private static final long serialVersionUID = 1L;

    private final ClassInstanceCreation wrappee;

    private final static IStrategoConstructor CTOR = new ASTCtor("ClassInstanceCreation", 4);

    WrappedClassInstanceCreation(ClassInstanceCreation wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        switch (index) {
        case 0:
            return ECJFactory.wrapExpression(wrappee.getExpression());
        case 1:
            return ECJFactory.wrapType(wrappee.getType());
        case 2:
            return ECJFactory.wrap(wrappee.getAnonymousClassDeclaration());
        case 3:
            return ECJFactory.wrap(wrappee.arguments());
        }

        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ClassInstanceCreation getWrappee() {
        return wrappee;
    }
}

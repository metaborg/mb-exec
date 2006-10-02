/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedClassInstanceCreation extends WrappedAppl {

    private final ClassInstanceCreation wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("ClassInstanceCreation", 4);
    
    WrappedClassInstanceCreation(ClassInstanceCreation wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return WrappedECJFactory.wrapExpression(wrappee.getExpression());
        case 1:
            return WrappedECJFactory.wrapType(wrappee.getType());
        case 2:
            return WrappedECJFactory.wrap(wrappee.getAnonymousClassDeclaration());
        case 3:
            return WrappedECJFactory.wrap(wrappee.arguments());
        }
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ASTNode getWrappee() {
        return wrappee;
    }
}

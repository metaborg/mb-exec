/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.TypeDeclarationStatement;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedTypeDeclarationStatement extends WrappedAppl {

    private final TypeDeclarationStatement wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("TypeDeclarationStatement", 1);
    
    WrappedTypeDeclarationStatement(TypeDeclarationStatement wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return WrappedECJFactory.wrapTypeDecl(wrappee.getDeclaration());
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ASTNode getWrappee() {
        return wrappee;
    }
}

/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedTypeDeclaration extends WrappedAppl {

    private final TypeDeclaration wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("TypeDeclaration", 6); 
    
    protected WrappedTypeDeclaration(TypeDeclaration wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return WrappedECJFactory.wrap(wrappee.getModifiers());
        case 1:
            return WrappedECJFactory.wrapName(wrappee.getName());
        case 2:
            return WrappedECJFactory.wrap(wrappee.typeParameters());
        case 3:
            return WrappedECJFactory.wrapType(wrappee.getSuperclassType());
        case 4:
            return WrappedECJFactory.wrap(wrappee.superInterfaceTypes());
        case 5:
            return WrappedECJFactory.wrap(wrappee.bodyDeclarations());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ASTNode getWrappee() {
        // TODO Auto-generated method stub
        return null;
    }

    public IStrategoTerm[] getArguments() {
        // TODO Auto-generated method stub
        return null;
    }

}

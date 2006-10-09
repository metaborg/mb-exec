/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedEnumDeclaration extends WrappedASTNode {

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
    public ASTNode getWrappee() {
        return wrappee;
    }

}

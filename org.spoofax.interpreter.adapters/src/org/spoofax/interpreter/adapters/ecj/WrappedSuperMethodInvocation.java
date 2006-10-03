/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedSuperMethodInvocation extends WrappedAppl {

    private final SuperMethodInvocation wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("SuperMethodInvocation", 4);
    
    WrappedSuperMethodInvocation(SuperMethodInvocation wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return WrappedECJFactory.wrapName(wrappee.getQualifier());
        case 1:
            return WrappedECJFactory.wrap(wrappee.typeArguments());
        case 2:
            return WrappedECJFactory.wrap(wrappee.getName());
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

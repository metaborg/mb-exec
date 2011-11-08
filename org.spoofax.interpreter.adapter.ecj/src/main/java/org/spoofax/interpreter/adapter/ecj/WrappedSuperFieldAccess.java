/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedSuperFieldAccess extends WrappedExpression {

    private static final long serialVersionUID = 1L;

    private final SuperFieldAccess wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("SuperFieldAccess", 2);
    
    WrappedSuperFieldAccess(SuperFieldAccess wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrapName(wrappee.getQualifier());
        case 1:
            return ECJFactory.wrap(wrappee.getName());
        }
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public SuperFieldAccess getWrappee() {
        return wrappee;
    }
}

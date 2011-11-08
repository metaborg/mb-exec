/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.TryStatement;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedTryStatement extends WrappedStatement {

    private static final long serialVersionUID = 1L;

    private final TryStatement wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("TryStatement", 3);
    
    WrappedTryStatement(TryStatement wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.getBody());
        case 1:
            return ECJFactory.wrap(wrappee.catchClauses());
        case 2:
            return ECJFactory.wrap(wrappee.getFinally());
        }
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public TryStatement getWrappee() {
        return wrappee;
    }
}

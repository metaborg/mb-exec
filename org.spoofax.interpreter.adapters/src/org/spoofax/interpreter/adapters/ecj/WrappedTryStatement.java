/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.TryStatement;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedTryStatement extends WrappedASTNode {

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
    public ASTNode getWrappee() {
        return wrappee;
    }
}
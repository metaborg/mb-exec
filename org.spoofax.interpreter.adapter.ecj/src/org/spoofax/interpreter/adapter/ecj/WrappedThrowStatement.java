/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.ThrowStatement;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedThrowStatement extends WrappedStatement {

    private final ThrowStatement wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("ThrowStatement", 1);
    
    WrappedThrowStatement(ThrowStatement wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0) 
            return ECJFactory.wrapExpression(wrappee.getExpression());
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ThrowStatement getWrappee() {
        return wrappee;
    }
}

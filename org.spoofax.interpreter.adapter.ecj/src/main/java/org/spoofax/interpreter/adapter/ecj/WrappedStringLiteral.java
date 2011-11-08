/*
 * Created on 1. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.StringLiteral;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedStringLiteral extends WrappedExpression {

    private static final long serialVersionUID = 1L;

    private final StringLiteral wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("StringLiteral", 1); 
        
    WrappedStringLiteral(StringLiteral wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrap(wrappee.getLiteralValue());
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public StringLiteral getWrappee() {
        return wrappee;
    }

}

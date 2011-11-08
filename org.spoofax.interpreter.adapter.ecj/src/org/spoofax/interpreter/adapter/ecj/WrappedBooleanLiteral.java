/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedBooleanLiteral extends WrappedExpression {
    
    private static final long serialVersionUID = 1L;

    private final BooleanLiteral wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("BooleanLiteral", 1);
    
    WrappedBooleanLiteral(BooleanLiteral wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            // FIXME Translate to True/False 
            return ECJFactory.wrap(wrappee.booleanValue() ? 1 : 0);
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public BooleanLiteral getWrappee() {
        return wrappee;
    }

}

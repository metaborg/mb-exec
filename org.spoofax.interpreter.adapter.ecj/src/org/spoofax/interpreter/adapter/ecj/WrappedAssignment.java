/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.Assignment;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedAssignment extends WrappedExpression {

    private final Assignment wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("Assignment", 2);
    
    WrappedAssignment(Assignment wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrapExpression(wrappee.getLeftHandSide());
        case 1:
            return ECJFactory.wrapExpression(wrappee.getRightHandSide());
        }

        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public Assignment getWrappee() {
        return wrappee;
    }

}

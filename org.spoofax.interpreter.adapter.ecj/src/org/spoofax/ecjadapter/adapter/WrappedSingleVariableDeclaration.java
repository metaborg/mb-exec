/*
 * Created on 1. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedSingleVariableDeclaration extends WrappedVariableDeclaration {

    private final SingleVariableDeclaration wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("SingleVariableDeclaration", 5);
    
    WrappedSingleVariableDeclaration(SingleVariableDeclaration wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.modifiers());
        case 1:
            return ECJFactory.wrapType(wrappee.getType());
        case 2:
            return ECJFactory.wrap(wrappee.getName());
        case 3:
            return ECJFactory.wrap(wrappee.getExtraDimensions());
        case 4:
            return ECJFactory.wrapExpression(wrappee.getInitializer());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public SingleVariableDeclaration getWrappee() {
        return wrappee;
    }
}

/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.eclipse.jdt.core.dom.ArrayType;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedArrayType extends WrappedType {

    private final ArrayType wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("ArrayType", 3);
    
    WrappedArrayType(ArrayType wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrapType(wrappee.getComponentType());
        case 1:
            return ECJFactory.wrap(wrappee.getDimensions());
        case 2:
            return ECJFactory.wrapType(wrappee.getElementType());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ArrayType getWrappee() {
        return wrappee;
    }

}

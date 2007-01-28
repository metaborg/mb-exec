/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.WildcardType;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedWildcardType extends WrappedType {

    // FIXME isUpperBound()
    private final WildcardType wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("WildcardType", 1);
    
    WrappedWildcardType(WildcardType wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrapType(wrappee.getBound());
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public WildcardType getWrappee() {
        return wrappee;
    }

}

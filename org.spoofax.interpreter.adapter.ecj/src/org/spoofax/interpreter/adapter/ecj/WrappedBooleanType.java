/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.PrimitiveType;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedBooleanType extends WrappedASTNode {

    private final PrimitiveType wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("BooleanType", 0);
    
    WrappedBooleanType(PrimitiveType wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        return null;
    }

    @Override
    public PrimitiveType getWrappee() {
        return wrappee;
    }

}

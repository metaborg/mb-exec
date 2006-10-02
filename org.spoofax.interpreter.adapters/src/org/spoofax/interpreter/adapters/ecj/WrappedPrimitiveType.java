/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedPrimitiveType extends WrappedAppl {

    // FIXME should we even keep this?
    
    private final PrimitiveType wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("PrimitiveType", 1);
    
    WrappedPrimitiveType(PrimitiveType wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    @Override
    public ASTNode getWrappee() {
        return wrappee;
    }
}

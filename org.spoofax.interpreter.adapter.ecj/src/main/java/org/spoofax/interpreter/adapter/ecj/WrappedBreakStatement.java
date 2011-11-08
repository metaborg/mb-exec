/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.BreakStatement;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedBreakStatement extends WrappedStatement {
    
    private static final long serialVersionUID = 1L;

    private final BreakStatement wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("BreakStatement", 1);
    
    WrappedBreakStatement(BreakStatement wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrapName(wrappee.getLabel());
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public BreakStatement getWrappee() {
        return wrappee;
    }

}

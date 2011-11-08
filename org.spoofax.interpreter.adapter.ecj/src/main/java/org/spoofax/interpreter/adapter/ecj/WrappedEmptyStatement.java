/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.EmptyStatement;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedEmptyStatement extends WrappedStatement {
    
    private static final long serialVersionUID = 1L;

    private final EmptyStatement wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("EmptyStatement", 0);
    
    WrappedEmptyStatement(EmptyStatement wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public EmptyStatement getWrappee() {
        return wrappee;
    }
}

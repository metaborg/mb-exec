/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.MethodRefParameter;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedMethodRefParameter extends WrappedASTNode {

    private static final long serialVersionUID = 1L;

    private final MethodRefParameter wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("MethodRefParameter", 2);
    
    WrappedMethodRefParameter(MethodRefParameter wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrapType(wrappee.getType());
        case 1:
            return ECJFactory.wrap(wrappee.getName());
            // FIXME Varags
        }
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public MethodRefParameter getWrappee() {
        return wrappee;
    }
}

/*
 * Created on 28. feb.. 2007
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.ICompilationUnit;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedICompilationUnit extends AbstractWrappedECJNode {
    
    private static final long serialVersionUID = 1L;

    private final ICompilationUnit wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("ICompilationUnit", 2);

    WrappedICompilationUnit(ICompilationUnit wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.getElementName());
        case 1:
            return ECJFactory.wrap(wrappee.hashCode());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    
    @Override
    public ICompilationUnit getWrappee() {
        return wrappee;
    }

}

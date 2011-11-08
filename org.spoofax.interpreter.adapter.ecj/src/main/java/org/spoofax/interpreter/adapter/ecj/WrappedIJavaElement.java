/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.IJavaElement;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedIJavaElement extends AbstractECJAppl {
    
    private static final long serialVersionUID = 1L;

    private final IJavaElement wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("IJavaElement", 1);
    
    WrappedIJavaElement(IJavaElement wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    protected WrappedIJavaElement(IStrategoConstructor ctor, IJavaElement wrappee) {
    	super(ctor);
    	this.wrappee = wrappee;
	}

	public IJavaElement getWrappee() {
        return wrappee;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0) {
            return ECJFactory.wrap(wrappee.hashCode());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

}

/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.IJavaElement;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedIJavaElement extends AbstractECJAppl {

    private final IJavaElement wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("IJavaElement", 1);
    
    WrappedIJavaElement(IJavaElement wrappee) {
        super(CTOR);
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

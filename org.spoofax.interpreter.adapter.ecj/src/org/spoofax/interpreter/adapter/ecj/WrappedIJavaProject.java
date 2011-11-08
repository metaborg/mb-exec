/*
 * Created on 10. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.IJavaProject;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedIJavaProject extends WrappedIJavaElement {
    
    private static final long serialVersionUID = 1L;

    private final IJavaProject wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("ECJJavaProject", 2);
    
    WrappedIJavaProject(IJavaProject wrappee) {
        super(CTOR, wrappee);
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

    public IJavaProject getWrappee() {
        return wrappee;
    }

}

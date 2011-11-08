/*
 * Created on 10. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.core.resources.IProject;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedIProject extends AbstractECJAppl {

    private static final long serialVersionUID = 1L;

    private final IProject wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("ECJProject", 2);
    
    WrappedIProject(IProject wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.getName());
        case 1:
            return ECJFactory.wrap(wrappee.hashCode());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public IProject getWrappee() {
        return wrappee;
    }

    // FIXME use hashtable instead -- preserves project equality tests
}

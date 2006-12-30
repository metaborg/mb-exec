/*
 * Created on 10. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.eclipse.core.resources.IProject;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedProject extends AbstractECJAppl {

    private final IProject wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("ECJProject", 1);
    
    WrappedProject(IProject wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrap(wrappee.getName());
        throw new ArrayIndexOutOfBoundsException();
    }

    public IProject getWrappee() {
        return wrappee;
    }

    // FIXME use hashtable instead -- preserves project equality tests
}

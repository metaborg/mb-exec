/*
 * Created on 24. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.interpreter.terms.IStrategoConstructor;


public abstract class AbstractWrappedECJNode extends AbstractECJAppl {

    private static final long serialVersionUID = 1L;

    protected AbstractWrappedECJNode(IStrategoConstructor constructor) {
        super(constructor);
    }

    public abstract Object getWrappee();
    
}

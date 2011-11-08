/*
 * Created on 24. jan.. 2007
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
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

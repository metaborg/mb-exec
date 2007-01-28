/*
 * Created on 24. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.spoofax.interpreter.terms.IStrategoConstructor;


public abstract class WrappedECJNode extends AbstractECJAppl {

    protected WrappedECJNode(IStrategoConstructor constructor) {
        super(constructor);
    }

    public abstract Object getWrappee();
}

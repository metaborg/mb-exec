/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.eclipse.jdt.core.dom.IBinding;
import org.spoofax.interpreter.terms.IStrategoConstructor;


public abstract class AbstractWrappedBinding extends AbstractECJAppl {

    protected AbstractWrappedBinding(IStrategoConstructor constructor) {
        super(constructor);
    }

    public abstract IBinding getWrappee();
}

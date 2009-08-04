/*
 * Created on 24. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.interpreter.terms.BasicTermFactory;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoList;


public abstract class WrappedECJNode extends AbstractECJAppl {

    protected WrappedECJNode(IStrategoConstructor constructor) {
        super(constructor);
    }

    public abstract Object getWrappee();
    
    public IStrategoList getAnnotations() {
    	return BasicTermFactory.EMPTY_LIST;
    }
}

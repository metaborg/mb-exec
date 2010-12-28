/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.io.AbstractIOTermFactory;

public abstract class AbstractECJNode implements IStrategoTerm {
	public IStrategoList getAnnotations() {
		return AbstractIOTermFactory.EMPTY_LIST;
	}
    
    public int getStorageType() {
        return MUTABLE;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof IStrategoTerm))
            return false;
        return match((IStrategoTerm)obj);
    }
}

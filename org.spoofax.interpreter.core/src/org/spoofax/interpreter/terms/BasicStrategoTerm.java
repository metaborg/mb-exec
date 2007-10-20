/*
 * Created on 2. feb.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;

public abstract class BasicStrategoTerm implements IStrategoTerm {


    public boolean match(IStrategoTerm second) {
        return doSlowMatch(second);
    }

    protected abstract boolean doSlowMatch(IStrategoTerm second);
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof IStrategoTerm))
            return false;
        return match((IStrategoTerm)obj);
    }
    
    @Override
    public abstract int hashCode();
}

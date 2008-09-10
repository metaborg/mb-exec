/*
 * Created on 2. feb.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;

public abstract class BasicStrategoTerm implements IStrategoTerm, Cloneable {

    private IStrategoList annotations;
    
    protected BasicStrategoTerm(IStrategoList annotations) {
        this.annotations = annotations;
    }

    public boolean match(IStrategoTerm second) {
        return this == second || doSlowMatch(second);
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
    
    @Override
    protected BasicStrategoTerm clone() {
        try {
            return (BasicStrategoTerm) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e); // silly checked exceptions...
        }
    }
    
    public IStrategoList getAnnotations() {
        return annotations == null ? BasicTermFactory.EMPTY_LIST : annotations;
    }
    
    protected void internalSetAnnotations(IStrategoList annotations) {
        this.annotations = annotations;
    }
}

/*
 * Created on 23. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.aterm;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTuple;

import aterm.ATerm;
import aterm.ATermAppl;

public class WrappedATermTuple extends WrappedATerm implements IStrategoTuple {

    private ATermAppl tuple;
    
    WrappedATermTuple(ATermAppl tuple) {
        this.tuple = tuple;
    }
    
    @Override
    ATerm getATerm() {
        return tuple;
    }

    public IStrategoTerm getSubterm(int index) {
        return WrappedATermFactory.wrapTerm((ATerm)tuple.getChildAt(index));
    }

    public int getSubtermCount() {
        return tuple.getChildCount();
    }

    public int size() {
        return getSubtermCount();
    }
    
    public int getTermType() {
        return IStrategoTerm.TUPLE;
    }

    public boolean match(IStrategoTerm second) {
        return equals(second);
    }

    @Override
    public boolean equals(Object second) {
        if(second instanceof WrappedATerm) {
            if(second instanceof WrappedATermTuple) {
                return ((WrappedATermTuple)second).tuple == tuple;
            }
            return false;
        }
        return slowCompare(second);
    }
    
    @Override
    public String toString() {
        return tuple.toString();
    }

    public IStrategoTerm get(int index) {
        return getSubterm(index);
    }

    @Override
    protected boolean slowCompare(Object second) {
        if(!(second instanceof IStrategoTuple))
            return false;
        IStrategoTuple snd = (IStrategoTuple)second;
        if(snd.getSubtermCount() != getSubtermCount())
            return false;
        for(int i = 0, sz = getSubtermCount(); i < sz; i++)
            if(!getSubterm(i).equals(snd.getSubterm(i)))
                return false;
        return true;
    }
}
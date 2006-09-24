/*
 * Created on 23. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms.aterm;

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
}

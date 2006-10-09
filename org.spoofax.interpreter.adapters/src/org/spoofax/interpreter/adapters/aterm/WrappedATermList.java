/*
 * Created on 23. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.aterm;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoList;

import aterm.ATerm;
import aterm.ATermList;

public class WrappedATermList extends WrappedATerm implements IStrategoList {

    private ATermList list;
    
    WrappedATermList(ATermList list) {
        this.list = list;
    }
    
    public IStrategoTerm get(int i) {
        return WrappedATermFactory.wrapTerm((ATerm)list.getChildAt(i));
    }

    public IStrategoList prepend(IStrategoTerm prefix) {
        if(prefix instanceof WrappedATerm) {
            return new WrappedATermList(list.insert(((WrappedATerm)prefix).getATerm()));
        }
        throw new WrapperException("Is " + prefix.getClass());    
       }

    public int size() {
        return list.getChildCount();
    }

    public IStrategoTerm getSubterm(int index) {
        return WrappedATermFactory.wrapTerm((ATerm)list.getChildAt(index));
    }

    public int getSubtermCount() {
        return list.getChildCount();
    }

    public int getTermType() {
        return IStrategoTerm.LIST;
    }

    public boolean match(IStrategoTerm second) {
        return equals(second);
    }

    @Override
    public boolean equals(Object second) {
        if(second instanceof WrappedATerm) {
            if(second instanceof WrappedATermList) {
                return ((WrappedATermList)second).list == list;
            }
            return false;
        }
        return slowCompare(second);
    }

    @Override
    ATermList getATerm() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public IStrategoTerm head() {
        // FIXME should have a null check
        return WrappedATermFactory.wrapTerm(list.getFirst());
    }

    public IStrategoList tail() {
        return new WrappedATermList(list.getNext());
    }
}

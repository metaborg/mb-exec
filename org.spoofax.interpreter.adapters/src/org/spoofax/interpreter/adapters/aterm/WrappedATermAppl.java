/*
 * Created on 23. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.aterm;

import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

import aterm.ATerm;
import aterm.ATermAppl;

public class WrappedATermAppl extends WrappedATerm implements IStrategoAppl {

    private ATermAppl appl;
    
    public WrappedATermAppl(ATermAppl appl) {
        this.appl = appl;
    }
    
    public IStrategoTerm[] getArguments() {
        // FIXME memoize
        IStrategoTerm[] ret = new IStrategoTerm[getSubtermCount()];
        ATerm[] args = appl.getArgumentArray();
        for(int i = 0; i < args.length; i++) {
            ret[i] = WrappedATermFactory.wrapTerm(args[i]);
        }
        return ret;
    }

    public IStrategoConstructor getConstructor() {
        // FIXME memoize
        return new WrappedAFun(appl.getAFun());
    }

    public IStrategoTerm getSubterm(int index) {
        // FIXME memoize
        return WrappedATermFactory.wrapTerm((ATerm)appl.getChildAt(index));
    }

    public int getSubtermCount() {
        return appl.getChildCount();
    }

    public int getTermType() {
        return IStrategoTerm.APPL;
    }

    public boolean match(IStrategoTerm second) {
        return equals(second);
    }
    
    @Override
    public boolean equals(Object second) {
        if(second instanceof WrappedATerm) {
            if(second instanceof WrappedATermAppl) {
                return ((WrappedATermAppl)second).appl == appl;
            }
            return false;
        }
        return slowCompare(second);
    }

    @Override
    ATerm getATerm() {
        return appl;
    }

    @Override
    public String toString() {
         return appl.toString();
    }

    @Override
    protected boolean slowCompare(Object second) {
        if(!(second instanceof IStrategoAppl))
            return false;
        IStrategoAppl snd = (IStrategoAppl) second;
        if(!snd.getConstructor().equals(getConstructor()))
            return false;
        for(int i = 0, sz = getSubtermCount(); i < sz; i++) {
            if(!snd.getSubterm(i).equals(getSubterm(i)))
                return false;
        }
        return true;
    }
}
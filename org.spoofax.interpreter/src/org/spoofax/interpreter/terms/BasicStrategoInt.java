/*
 * Created on 28. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;

import org.spoofax.NotImplementedException;

public class BasicStrategoInt implements IStrategoInt {

    final int value;
    
    BasicStrategoInt(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

    public IStrategoTerm[] getAllSubterms() {
        return BasicTermFactory.EMPTY;
    }

    public IStrategoTerm getSubterm(int index) {
        throw new ArrayIndexOutOfBoundsException();
    }

    public int getSubtermCount() {
        return 0;
    }

    public int getTermType() {
        return IStrategoTerm.INT;
    }

    public boolean match(IStrategoTerm second) {
        return doSlowMatch(second);
    }

    private boolean doSlowMatch(IStrategoTerm second) {
        if(second.getTermType() != IStrategoTerm.INT)
            return false;
        return value == ((IStrategoInt)second).getValue(); 
    }

    public void prettyPrint(ITermPrinter pp) {
        pp.print("" + value);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof IStrategoTerm))
            return false;
        return match((IStrategoTerm) obj);
    }
}

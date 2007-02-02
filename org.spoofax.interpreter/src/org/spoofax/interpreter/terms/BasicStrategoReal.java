/*
 * Created on 28. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;


public class BasicStrategoReal implements IStrategoReal {

    final double value;
    
    BasicStrategoReal(double value) {
        this.value = value;
    }
    
    public double getValue() {
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
        return IStrategoTerm.REAL;
    }

    public boolean match(IStrategoTerm second) {
        return doSlowMatch(second);
    }
    
    protected boolean doSlowMatch(IStrategoTerm second) {
        if(second.getTermType() != IStrategoTerm.REAL)
            return false;
        return value == ((IStrategoReal)second).getValue();
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

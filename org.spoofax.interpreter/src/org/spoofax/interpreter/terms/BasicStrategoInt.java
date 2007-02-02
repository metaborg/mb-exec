/*
 * Created on 28. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;


public class BasicStrategoInt extends BasicStrategoTerm implements IStrategoInt {

    private final int value;
    
    protected BasicStrategoInt(int value) {
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

    @Override
    protected boolean doSlowMatch(IStrategoTerm second) {
        if(second.getTermType() != IStrategoTerm.INT)
            return false;
        return value == ((IStrategoInt)second).getValue(); 
    }

    public void prettyPrint(ITermPrinter pp) {
        pp.print("" + value);
    }

    @Override
    public String toString() {
        return "" + value;
    }
}

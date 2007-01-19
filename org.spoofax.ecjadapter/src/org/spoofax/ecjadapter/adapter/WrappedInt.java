/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.PrettyPrinter;

public class WrappedInt implements IStrategoInt {

    private final int value;

    WrappedInt(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public IStrategoTerm getSubterm(int index) {
        return null;
    }

    public int getSubtermCount() {
        return 0;
    }

    public int getTermType() {
        return IStrategoTerm.INT;
    }

    public boolean match(IStrategoTerm second) {
        throw new NotImplementedException();
    }

    public void prettyPrint(PrettyPrinter pp) {
        pp.print("" + value);
    }

    public IStrategoTerm[] getAllSubterms() {
        return null;
    }

}

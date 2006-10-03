/*
 * Created on 4. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTuple;
import org.spoofax.interpreter.terms.PrettyPrinter;

public class WrappedTuple implements IStrategoTuple {

    private IStrategoTerm[] kids;
    
    WrappedTuple(IStrategoTerm[] kids) {
        this.kids = kids;
    }
    
    public IStrategoTerm get(int index) {
        return kids[index];
    }

    public int size() {
        return kids.length;
    }

    public IStrategoTerm getSubterm(int index) {
        return kids[index];
    }

    public int getSubtermCount() {
        return kids.length;
    }

    public int getTermType() {
        return IStrategoTerm.TUPLE;
    }

    public boolean match(IStrategoTerm second) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public void prettyPrint(PrettyPrinter pp) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

}

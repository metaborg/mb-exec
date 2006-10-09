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
        if(!(second instanceof WrappedTuple))
            return false;
        
        WrappedTuple t = (WrappedTuple)second;
        
        if(kids.length != kids.length)
            return false;
        
        for(int i = 0, sz = t.size(); i < sz; i++) {
            if(!kids[i].match(t.kids[i]))
                return false;
        }
        
        return true;
    }

    public void prettyPrint(PrettyPrinter pp) {
        int sz = size();
        if(sz > 0) {
            pp.println("(");
            pp.indent(2);
            get(0).prettyPrint(pp);
            for(int i = 1; i < sz; i++) {
                pp.print(", ");
                pp.nextIndentOff();
                get(i).prettyPrint(pp);
                pp.println("");
            }
            pp.println("");
            pp.print(")");
            pp.outdent(2);

        } else {
            pp.print("()");
        }
    }

}

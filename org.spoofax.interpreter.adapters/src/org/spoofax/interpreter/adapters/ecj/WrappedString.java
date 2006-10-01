/*
 * Created on 27. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.PrettyPrinter;

public class WrappedString implements IStrategoString {

    private String wrappee;
    
    WrappedString(String wrappee) {
        this.wrappee = wrappee;
    }

    WrappedString(char[] wrappee) {
        this.wrappee = String.valueOf(wrappee);
    }

    public String getValue() {
        return wrappee;
    }

    public IStrategoTerm getSubterm(int index) {
        return null;
    }

    public int getSubtermCount() {
        return 0;
    }

    public int getTermType() {
        return IStrategoString.STRING;
    }

    public boolean match(IStrategoTerm second) {
        throw new NotImplementedException();
    }

    public void  prettyPrint(PrettyPrinter pp) {
        pp.print("\"" + wrappee + "\"");
    }
}

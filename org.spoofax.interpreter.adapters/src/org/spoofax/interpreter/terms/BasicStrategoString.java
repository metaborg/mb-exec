/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;

public abstract class BasicStrategoString implements IStrategoString {

    protected final String value;
    
    protected BasicStrategoString(String value) {
        this.value = value;
    }
    
    public IStrategoTerm getSubterm(int index) {
        return null;
    }

    public int getSubtermCount() {
        return 0;
    }

    public int getTermType() {
        return IStrategoTerm.STRING;
    }

    public abstract boolean match(IStrategoTerm second);
    
    protected boolean doSlowMatch(IStrategoTerm second) {
        if(second instanceof IStrategoString) {
            IStrategoString snd = (IStrategoString) second;
            return value.equals(snd.getValue());
        }
        return false;
    }

    public String getValue() {
        return value;
    }
    
    public void prettyPrint(PrettyPrinter pp) {
        pp.print("\"" +  value + "\"");
    }        
}

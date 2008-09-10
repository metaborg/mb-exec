/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;


public class BasicStrategoString extends BasicStrategoTerm implements IStrategoString {

    private final String value;
    
    public BasicStrategoString(String value, IStrategoList annotations) {
        super(annotations);
        this.value = value;
    }
    
    public BasicStrategoString(String value) {
        this(value, null);
    }
    
    public IStrategoTerm getSubterm(int index) {
        throw new ArrayIndexOutOfBoundsException();
    }

    public IStrategoTerm[] getAllSubterms() {
        return BasicTermFactory.EMPTY;
    }
    
    public int getSubtermCount() {
        return 0;
    }

    public int getTermType() {
        return IStrategoTerm.STRING;
    }

    @Override
    protected boolean doSlowMatch(IStrategoTerm second) {
        if(second.getTermType() != IStrategoTerm.STRING)
            return false;
        
        IStrategoString snd = (IStrategoString) second;
        if (!value.equals(snd.stringValue()))
            return false;
        
        return getAnnotations().match(second.getAnnotations());
    }

    public String stringValue() {
        return value;
    }
    
    public void prettyPrint(ITermPrinter pp) {
        // The abundance of backslashes are required by the RE compiler
        pp.print("\"" + value.replaceAll("\"", "\\\\\\\"") + "\"");
    }
 
    @Override
    public String toString() {
        return "\"" + value + "\""; 
    }
    
    @Override
    public int hashCode() {
        return value.hashCode();
    }
    
}

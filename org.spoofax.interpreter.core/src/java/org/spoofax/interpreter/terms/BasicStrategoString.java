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
        pp.print("\"");
        pp.print(stringValue().replace("\\", "\\\\").replace("\"", "\\\"")
                .replace("\n", "\\n").replace("\r", "\\r"));
        pp.print("\"");
        printAnnotations(pp);
    }
 
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\"");
        result.append(stringValue().replace("\\", "\\\\").replace("\"", "\\\"")
                .replace("\n", "\\n").replace("\r", "\\r"));
        result.append("\"");
        appendAnnotations(result);
        return result.toString();
    }
    
    @Override
    public int hashCode() {
        return value.hashCode();
    }
    
}

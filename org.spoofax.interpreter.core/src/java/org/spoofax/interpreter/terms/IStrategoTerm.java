/*
 * Created on 30. aug.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;


public interface IStrategoTerm {

    public static final int APPL = 1;
    public static final int LIST = 2;
    public static final int INT = 3;
    public static final int REAL = 4;
    public static final int STRING = 5;
    public static final int CTOR = 6;
    public static final int TUPLE = 7;
    public static final int REF = 8;

    public int getSubtermCount();
    public IStrategoTerm getSubterm(int index);
    public IStrategoTerm[] getAllSubterms();
    public int getTermType();
    
    public IStrategoList getAnnotations();
    
    public boolean match(IStrategoTerm second);
    
    public void prettyPrint(ITermPrinter pp);
}

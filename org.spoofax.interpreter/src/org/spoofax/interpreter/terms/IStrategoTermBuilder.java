/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;

import java.util.Collection;


public interface IStrategoTermBuilder {
    
    // FIXME remote quotation param: use IStrategoString instead
    public IStrategoConstructor makeConstructor(String string, int arity, boolean quoted);

    public IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoTermList kids);
    public IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoTerm... terms);

    public IStrategoInt makeInt(int i);
    public IStrategoReal makeReal(double d);
    public IStrategoTuple makeTuple(IStrategoTerm... terms);
    public IStrategoString makeString(String s);
    public IStrategoTermList makeList(IStrategoTerm... terms);
    public IStrategoTermList makeList(Collection<IStrategoTerm> terms);

    public boolean hasConstructor(String s, int i);
    
}



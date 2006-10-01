/*
 * Created on 30. aug.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;


public interface IStrategoList extends IStrategoTerm {

    public IStrategoTerm get(int index);

    public int size();

    public IStrategoList append(IStrategoTerm postfix);
    
    public IStrategoList insert(IStrategoTerm prefix);

    public IStrategoTerm head();
    public IStrategoList tail();

}

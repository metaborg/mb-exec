/*
 * Created on 30. aug.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;

public interface IStrategoConstructor extends IStrategoTerm {

    public IStrategoAppl instantiate(IStrategoList kids);

    public String getName();
    public int getArity();

}

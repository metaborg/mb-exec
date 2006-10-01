package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.spoofax.interpreter.terms.IStrategoTerm;
/*
 * Created on 26. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */

public abstract class WrappedASTNode implements IStrategoTerm {

    public abstract IStrategoTerm getSubterm(int index);

    public abstract int getSubtermCount();

    public abstract int getTermType();

    public abstract boolean match(IStrategoTerm second);

    public abstract ASTNode getWrappee();

}

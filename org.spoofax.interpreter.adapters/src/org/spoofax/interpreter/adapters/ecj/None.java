/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class None extends WrappedASTNode {


    private final static IStrategoTerm[] EMPTY = new IStrategoTerm[0];
    private final static IStrategoConstructor CTOR = new ASTCtor("None", 0); 
    
    final static None INSTANCE = new None();
    
    protected None() {
        super(CTOR);
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        return null;
    }

    @Override
    public ASTNode getWrappee() {
        return null;
    }

    public IStrategoTerm[] getArguments() {
        return EMPTY;
    }

}

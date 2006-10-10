/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.Name;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedName extends WrappedASTNode {

    private final static IStrategoConstructor CTOR = new ASTCtor("Name", 1);
    private final Name wrappee;
    
    public WrappedName(Name wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            // FIXME ??
            return new WrappedString(wrappee.getFullyQualifiedName());
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public Name getWrappee() {
        return wrappee;
    }
}

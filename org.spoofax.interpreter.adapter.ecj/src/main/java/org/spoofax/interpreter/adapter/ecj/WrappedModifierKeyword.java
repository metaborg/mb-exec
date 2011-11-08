/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.Modifier;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedModifierKeyword extends AbstractWrappedECJNode {

    private static final long serialVersionUID = 1L;

    private final Modifier.ModifierKeyword wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("ModifierKeyword", 1);
    
    WrappedModifierKeyword(Modifier.ModifierKeyword wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrap(wrappee.toFlagValue());
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public Modifier.ModifierKeyword getWrappee() {
        return wrappee;
    }

}

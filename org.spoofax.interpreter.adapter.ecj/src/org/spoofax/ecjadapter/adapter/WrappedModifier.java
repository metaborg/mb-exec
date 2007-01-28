/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.eclipse.jdt.core.dom.Modifier;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedModifier extends WrappedASTNode implements IWrappedExtendedModifier {

    private final Modifier wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("Modifier", 1);
    
    WrappedModifier(Modifier wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrap(wrappee.getKeyword());
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public Modifier getWrappee() {
        return wrappee;
    }
}

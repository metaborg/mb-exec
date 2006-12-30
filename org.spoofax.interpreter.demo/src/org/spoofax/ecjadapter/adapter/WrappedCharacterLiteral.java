/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedCharacterLiteral extends WrappedASTNode {

    private final CharacterLiteral wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("CharacterLiteral", 1);
    
    WrappedCharacterLiteral(CharacterLiteral wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrap(wrappee.charValue());
        
        throw new ArrayIndexOutOfBoundsException(); 
    }

    @Override
    public ASTNode getWrappee() {
        return wrappee;
    }
}

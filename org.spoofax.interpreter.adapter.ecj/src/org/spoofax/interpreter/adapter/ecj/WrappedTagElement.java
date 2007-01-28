/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.TagElement;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedTagElement extends WrappedASTNode {

    // FIXME nested
    
    private final TagElement wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("TagElement", 2);
    
    WrappedTagElement(TagElement wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.getTagName());
        case 1:
            return ECJFactory.wrap(wrappee.fragments());
        }
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public TagElement getWrappee() {
        return wrappee;
    }
}

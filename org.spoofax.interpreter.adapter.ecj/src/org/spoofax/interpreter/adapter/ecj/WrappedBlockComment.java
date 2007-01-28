/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.BlockComment;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedBlockComment extends WrappedComment {

    // FIXME where's the content?
    private final BlockComment wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("BlockComment", 0);
    
    WrappedBlockComment(BlockComment wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
	    throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public BlockComment getWrappee() {
        return wrappee;
    }

}

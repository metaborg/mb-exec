/*
 * Created on 25. des.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.interpreter.terms.IStrategoConstructor;

public abstract class WrappedComment extends WrappedASTNode {
    
    private static final long serialVersionUID = 1L;

    protected WrappedComment(IStrategoConstructor constructor) {
        super(constructor);
    }
}

/*
 * Created on 25. des.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.Expression;
import org.spoofax.interpreter.terms.IStrategoConstructor;

public abstract class WrappedExpression extends WrappedASTNode {

    protected WrappedExpression(IStrategoConstructor constructor) {
        super(constructor);
    }
    
    public abstract Expression getWrappee();
}

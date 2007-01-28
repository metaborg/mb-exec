/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.Name;
import org.spoofax.interpreter.terms.IStrategoConstructor;

public abstract class WrappedName extends WrappedExpression {

    protected WrappedName(IStrategoConstructor constructor) {
        super(constructor);
    }
    
    public abstract Name getWrappee();
}

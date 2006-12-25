/*
 * Created on 25. des.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.spoofax.interpreter.terms.IStrategoConstructor;

public abstract class WrappedVariableDeclaration extends WrappedASTNode {

    protected WrappedVariableDeclaration(IStrategoConstructor constructor) {
        super(constructor);
    }
}

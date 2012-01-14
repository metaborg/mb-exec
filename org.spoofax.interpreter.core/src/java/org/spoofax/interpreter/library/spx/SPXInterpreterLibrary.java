/*
 * Created on 13. jan.. 2012
 *
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.spx;

import org.spoofax.interpreter.library.AbstractStrategoOperatorRegistry;

public class SPXInterpreterLibrary extends AbstractStrategoOperatorRegistry {

    public static final String NAME = "SPX_interpreter";

    public String getOperatorRegistryName() {
        return NAME;
    }

    public SPXInterpreterLibrary() {
        add(new SPX_interpreter_introspect_constructors());
        add(new SPX_interpreter_introspect_strategies());
    }
}

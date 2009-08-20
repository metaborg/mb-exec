/*
 * Created on 9. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_dynamic_rules_hashtable extends AbstractPrimitive {

    private final SSLLibrary library;

    protected SSL_dynamic_rules_hashtable(SSLLibrary library) {
        super("SSL_dynamic_rules_hashtable", 0, 0);
        this.library = library;
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

        env.setCurrent(library.getDynamicRuleTable());
        return true;
    }

}

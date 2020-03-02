/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.util.TermUtils;

public class SSL_is_string extends AbstractPrimitive {

    protected SSL_is_string() {
        super("SSL_is_string", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        return TermUtils.isString(targs[0]);
    }
}

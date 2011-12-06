/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_int_to_string extends AbstractPrimitive {

    protected SSL_int_to_string() {
        super("SSL_int_to_string", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) throws InterpreterException {

        if(!Tools.isTermInt(tvars[0]))
            return false;

        IStrategoInt a = (IStrategoInt) tvars[0];
        String s = String.valueOf(a.intValue());
        env.setCurrent(env.getFactory().makeString(s));
        return true;
    }
}

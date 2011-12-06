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
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_real_to_string extends AbstractPrimitive {

    protected SSL_real_to_string() {
        super("SSL_real_to_string", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {

        if (Tools.isTermInt(targs[0]))
            return new SSL_int_to_string().call(env, sargs, targs);
        if (!(Tools.isTermReal(targs[0])))
            return false;

        IStrategoReal a = (IStrategoReal) targs[0];
        String s = String.format("%.17g", a.realValue());
        env.setCurrent(env.getFactory().makeString(s));
        return true;
    }
}

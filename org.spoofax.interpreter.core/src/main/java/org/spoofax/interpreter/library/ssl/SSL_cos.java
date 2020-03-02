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
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.util.TermUtils;

public class SSL_cos extends AbstractPrimitive {

    protected SSL_cos() {
        super("SSL_cos", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        if(!(TermUtils.isReal(targs[0])))
            return false;

        IStrategoReal a = (IStrategoReal) targs[0];
        env.setCurrent(env.getFactory().makeReal(Math.cos(a.realValue())));
        return true;
    }
}

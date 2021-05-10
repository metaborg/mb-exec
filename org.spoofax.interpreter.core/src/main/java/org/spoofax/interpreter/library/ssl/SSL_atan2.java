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

public class SSL_atan2 extends AbstractPrimitive {

    protected SSL_atan2() {
        super("SSL_atan2", 0, 2);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        if(!(TermUtils.isReal(targs[0]) && TermUtils.isReal(targs[1])))
            return false;

        IStrategoReal x = (IStrategoReal) targs[0];
        IStrategoReal y = (IStrategoReal) targs[1];
        env.setCurrent(env.getFactory().makeReal(Math.atan2(x.realValue(), y.realValue())));
        return true;
    }
}

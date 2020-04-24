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

public class SSL_subtr extends AbstractPrimitive {

    protected SSL_subtr() {
        super("SSL_subtr", 0, 2);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) throws InterpreterException {
        
        if(!TermUtils.isReal(tvars[0]))
            return false;
        if(!TermUtils.isReal(tvars[1]))
            return false;

        IStrategoReal a = (IStrategoReal) tvars[0];
        IStrategoReal b = (IStrategoReal) tvars[1];
        env.setCurrent(env.getFactory().makeReal(a.realValue() - b.realValue()));
        return true;
    }
}

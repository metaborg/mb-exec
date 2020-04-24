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
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.util.TermUtils;

public class SSL_addr extends AbstractPrimitive {

    protected SSL_addr() {
        super("SSL_addr", 0, 2);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) throws InterpreterException {
        double a, b;
        
        if(TermUtils.isReal(tvars[0])) {
            a = ((IStrategoReal) tvars[0]).realValue();     
        } else if (TermUtils.isInt(tvars[0])) {
            a = ((IStrategoInt) tvars[0]).intValue();
        } else {
            return false;
        }
        
        if(TermUtils.isReal(tvars[1])) {
            b = ((IStrategoReal) tvars[1]).realValue();
        } else if (TermUtils.isInt(tvars[1])) {
            b = ((IStrategoInt) tvars[1]).intValue();
        } else {
            return false;
        }

        env.setCurrent(env.getFactory().makeReal(a + b));
        return true;
    }
}

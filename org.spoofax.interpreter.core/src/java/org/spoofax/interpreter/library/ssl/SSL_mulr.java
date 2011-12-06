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
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_mulr extends AbstractPrimitive {

    protected SSL_mulr() {
        super("SSL_mulr", 0, 2);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] tvars) throws InterpreterException {
        double a, b;
        
        if(Tools.isTermReal(tvars[0])) {
            a = ((IStrategoReal) tvars[0]).realValue();     
        } else if (Tools.isTermInt(tvars[0])) {
            a = ((IStrategoInt) tvars[0]).intValue();
        } else {
            return false;
        }
        
        if(Tools.isTermReal(tvars[1])) {
            b = ((IStrategoReal) tvars[1]).realValue();
        } else if (Tools.isTermInt(tvars[1])) {
            b = ((IStrategoInt) tvars[1]).intValue();
        } else {
            return false;
        }

        env.setCurrent(env.getFactory().makeReal(a * b));
        return true;
    }
}

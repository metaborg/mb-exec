/*
 * Created on 29.aug.2008
 *
 * Copyright (c) 2008, Sander Vermolen <sandervermolen near gmail.com>
 * Copyright (c) 2008, Karl Trygve Kalleberg <karltk near strategoxt dot org> 
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

public class SSL_real extends AbstractPrimitive {

    protected SSL_real() {
        super("SSL_real", 0, 1);
    }

    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) throws InterpreterException {
        
        // If already real skip
        if(Tools.isTermReal(tvars[0])) {
            env.setCurrent(tvars[0]);
            return true;
        }

        // If int, do regular conversion
        if(Tools.isTermInt(tvars[0]))
        {
            IStrategoInt a = (IStrategoInt) tvars[0];
            env.setCurrent(env.getFactory().makeReal(a.intValue()));
            return true;
        }
        
        // If something else, try parsing the toString (in a desperate attempt)
        try {
            double d = Double.parseDouble(tvars[0].toString());
            env.setCurrent(env.getFactory().makeReal(d));
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}

/*
 * Created on 9. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_get_constructor extends AbstractPrimitive {

    public SSL_get_constructor() {
        super("SSL_get_constructor", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

        switch(tvars[0].getTermType()) {
        case IStrategoTerm.APPL:
            IStrategoAppl a = (IStrategoAppl) tvars[0];
            env.setCurrent(env.getFactory().makeString(a.getConstructor().getName()));
            return true;
        case IStrategoTerm.INT:
            return true;
        case IStrategoTerm.REAL:
            return true;
        case IStrategoTerm.LIST:
            env.setCurrent(env.getFactory().makeList());
            return true;
        case IStrategoTerm.STRING:
            return true;
        case IStrategoTerm.TUPLE:
            env.setCurrent(env.getFactory().makeString(""));
            return true;
        default:
            return false;
        }
    }

}

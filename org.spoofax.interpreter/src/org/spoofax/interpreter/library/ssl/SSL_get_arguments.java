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

public class SSL_get_arguments extends AbstractPrimitive {

    public SSL_get_arguments() {
        super("SSL_get_arguments", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        switch(tvars[0].getTermType()) {
        case IStrategoTerm.APPL:
            IStrategoAppl a = (IStrategoAppl) tvars[0];
            env.setCurrent(env.getFactory().makeList(a.getArguments()));
            return true;
        case IStrategoTerm.INT:
        case IStrategoTerm.REAL:
            env.setCurrent(env.getFactory().makeList());
            return true;
        case IStrategoTerm.LIST:
            return true;
        default:
            return false;
        }
    }

}

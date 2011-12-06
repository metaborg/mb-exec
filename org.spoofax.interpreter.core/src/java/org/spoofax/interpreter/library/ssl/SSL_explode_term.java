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
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_explode_term extends AbstractPrimitive {

    protected SSL_explode_term() {
        super("SSL_explode_term", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) throws InterpreterException {
        SSLLibrary op = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        op.get("SSL_get_constructor").call(env, svars, tvars);
        IStrategoTerm cons = env.current();

        op.get("SSL_get_arguments").call(env, svars, tvars);
        IStrategoTerm args = env.current();
        
        IStrategoTerm sl = env.getFactory().makeTuple(cons, args);
        env.setCurrent(sl);
        return true;
    }
}

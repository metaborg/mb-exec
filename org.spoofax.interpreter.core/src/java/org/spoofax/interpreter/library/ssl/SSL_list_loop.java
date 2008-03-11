/*
 * Created on 12. jan.. 2007
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
import org.spoofax.interpreter.stratego.CallT;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_list_loop extends AbstractPrimitive {

    SSL_list_loop() {
        super("SSL_list_loop", 1, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!Tools.isTermList(tvars[0]))
            return false;
        
        IStrategoTerm[] list = tvars[0].getAllSubterms();
        CallT s = (CallT) svars[0];
        Strategy[] sv = new Strategy[0];
        IStrategoTerm[] tv = new IStrategoTerm[0];
        
        IStrategoTerm saved = env.current();
        
        for(int i = 0, sz = list.length; i < sz; i++) {
            env.setCurrent(list[i]);
            if(!s.evaluateWithArgs(env, sv, tv))
                return false;
        }
        
        env.setCurrent(saved);
        return true;
    }

}

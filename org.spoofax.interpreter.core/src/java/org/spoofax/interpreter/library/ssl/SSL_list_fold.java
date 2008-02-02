/*
 * Created on 12. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
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

public class SSL_list_fold extends AbstractPrimitive {

    SSL_list_fold() {
        super("SSL_list_fold", 1, 2);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!Tools.isTermList(tvars[1]))
            return false;
        
        IStrategoTerm[] list = tvars[1].getAllSubterms();
        CallT s = (CallT) svars[0];
        Strategy[] sv = new Strategy[0];
        IStrategoTerm[] tv = new IStrategoTerm[1];
        tv[0] = tvars[0];
        
        env.setCurrent(tv[0]);
        for(int i = 0, sz = list.length; i < sz; i++) {
            env.setCurrent(list[i]);
            if(!s.evaluateWithArgs(env, sv, tv))
                return false;
            tv[0] = env.current();
        }
        return true;
    }

}

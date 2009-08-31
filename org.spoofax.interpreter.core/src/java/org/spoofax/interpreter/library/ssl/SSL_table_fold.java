/*
 * Created on 11. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.util.Map.Entry;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.CallT;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_table_fold extends AbstractPrimitive {

    public SSL_table_fold() {
        super("SSL_table_fold", 1, 2);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if (!(tvars[1] instanceof StrategoHashMap))
            return false;
        
        StrategoHashMap ht = (StrategoHashMap) tvars[1];
        
        IStrategoTerm result = tvars[0];
        CallT sdef = (CallT) svars[0];
        Strategy[] sv = new Strategy[0];
        IStrategoTerm[] tv = new IStrategoTerm[2];

        env.setCurrent(tvars[0]);
        
        for(Entry<IStrategoTerm,IStrategoTerm> e : ht.entrySet()) {
            tv[0] = e.getKey();
            tv[1] = e.getValue();
            if(!sdef.evaluateWithArgs(env, sv, tv))
                return false;
            result = env.current();  
        }
        
        env.setCurrent(result);
        return true;
    }

}

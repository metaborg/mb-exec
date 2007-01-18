/*
 * Created on 12. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.CallT;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_list_loop extends AbstractPrimitive {

    SSL_list_loop() {
        super("SSL_list_loop", 1, 1);
    }
    
    @Override
    public boolean call(IContext env, IConstruct[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!Tools.isTermList(tvars[0]))
            return false;
        
        IStrategoList l = (IStrategoList) tvars[0];
        CallT s = (CallT) svars[0];
        IConstruct[] sv = new IConstruct[0];
        IStrategoTerm[] tv = new IStrategoTerm[0];
        
        IStrategoTerm saved = env.current();
        
        while(!l.isEmpty()) {
            env.setCurrent(l.head());
            if(!s.evalWithArgs(env, sv, tv))
                return false;
            l = l.tail();
        }
        
        env.setCurrent(saved);
        return true;
    }

}

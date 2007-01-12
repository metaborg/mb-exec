/*
 * Created on 12. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.util.LinkedList;
import java.util.List;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.CallT;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_list_fold extends AbstractPrimitive {

    SSL_list_fold() {
        super("SSL_list_fold", 1, 2);
    }
    
    @Override
    public boolean call(IContext env, List<IConstruct> svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!Tools.isTermList(tvars[1]))
            return false;
        
        IStrategoList l = (IStrategoList) tvars[1];
        CallT s = (CallT) svars.get(0);
        List<IConstruct> sv = new LinkedList<IConstruct>();
        IStrategoTerm[] tv = new IStrategoTerm[1];
        tv[0] = tvars[0];
        
        env.setCurrent(tv[0]);
        while(!l.isEmpty()) {
            env.setCurrent(l.head());
            if(!s.evalWithArgs(env, sv, tv))
                return false;
            tv[0] = env.current();
            l = l.tail();
        }
        return true;
    }

}

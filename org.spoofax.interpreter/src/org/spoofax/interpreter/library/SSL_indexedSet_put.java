/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.List;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.SSL_indexedSet_create.IndexedSet;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_indexedSet_put extends Primitive {

    protected SSL_indexedSet_put() {
        super("SSL_indexedSet_put", 1, 2);
    }

    public boolean call(IContext env, List<Strategy> sargs, List<IStrategoTerm> targs)
            throws InterpreterException {

        if (!(Tools.isTermInt(targs.get(0))))
            return false;

        int ref = ((IStrategoInt)targs.get(0)).getValue();

        IndexedSet is = SSL_indexedSet_create.map.get(ref);
        if(is == null)
            return false;
        
        IStrategoTerm t = targs.get(1);
        Strategy s = sargs.get(0);
        if (is.containsValue(t)) {
            return s.eval(env);
        }

        int idx = is.put(t);
        env.setCurrent(env.getFactory().makeInt(idx));

        return true;
    }
}

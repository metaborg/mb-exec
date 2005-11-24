/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.List;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.SSL_indexedSet_create.ATermIndexedSet;
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;
import aterm.ATermInt;

public class SSL_indexedSet_put extends Primitive {

    protected SSL_indexedSet_put() {
        super("SSL_indexedSet_put", 1, 2);
    }

    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs)
            throws FatalError {
        debug("SSL_indexedSet_put");

        if (!Tools.isATermInt(targs.get(0)))
            return false;

        int ref = Tools.getATermInt((ATermInt) targs.get(0));

        ATermIndexedSet is = SSL_indexedSet_create.map.get(ref);
        if(is == null)
            return false;
        
        ATerm t = targs.get(1);
        if (is.containsValue(t)) {
            debug("" + sargs);
            throw new FatalError("Must call the interpreter here");
        }

        int idx = is.put(t);
        env.setCurrent(env.makeTerm(idx));

        return true;
    }
}

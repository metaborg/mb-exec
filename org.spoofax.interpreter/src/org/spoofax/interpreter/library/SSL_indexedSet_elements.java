/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.Collection;
import java.util.List;

import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.SSL_indexedSet_create.ATermIndexedSet;
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;
import aterm.ATermInt;

public class SSL_indexedSet_elements extends Primitive {

    protected SSL_indexedSet_elements() {
        super("SSL_indexedSet_elements", 0, 1);
    }

    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs)
            throws InterpreterException {

        if (!Tools.isATermInt(targs.get(0)))
            return false;

        int ref = Tools.getATermInt((ATermInt)targs.get(0));
        ATermIndexedSet is = SSL_indexedSet_create.map.get(ref);

        if (is == null)
            return false;

        Collection<ATerm> values = is.values();
        env.setCurrent(env.makeList(values));

        return true;
    }
}

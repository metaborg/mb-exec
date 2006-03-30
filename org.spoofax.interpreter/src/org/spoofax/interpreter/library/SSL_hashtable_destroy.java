/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.List;

import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;
import aterm.ATermInt;

public class SSL_hashtable_destroy extends Primitive {

    protected SSL_hashtable_destroy() {
        super("SSL_hashtable_destroy", 0, 1);
    }

    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs)
            throws InterpreterException {

        if (!Tools.isATermInt(targs.get(0)))
            return false;

        boolean res = SSL.removeHashtable(((ATermInt)targs.get(0)).getInt());
        env.setCurrent(targs.get(0));
        return res;
    }
}

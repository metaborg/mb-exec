/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.HashMap;
import java.util.List;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;
import aterm.ATermInt;

public class SSL_hashtable_create extends Primitive {

    protected class ATermHashtable extends HashMap<ATerm, ATerm> {

        private static final long serialVersionUID = -8193582031891397734L;

        public ATermHashtable(int initialSize, int maxLoad) {
            super(initialSize, 1.0f * maxLoad / 100);
        }

    }

    protected SSL_hashtable_create() {
        super("SSL_hashtable_create", 0, 2);
    }

    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs)
            throws FatalError {
        debug("SSL_hashtable_create");

        if (!Tools.isATermInt(targs.get(0)))
            return false;
        if (!Tools.isATermInt(targs.get(1)))
            return false;

        int initialSize = Tools.getATermInt((ATermInt) targs.get(0));
        int maxLoad = Tools.getATermInt((ATermInt) targs.get(1));

        int ref = SSL.registerHashtable(new ATermHashtable(initialSize, maxLoad));
        
        env.setCurrent(env.makeTerm(ref));
        return true;
    }
}

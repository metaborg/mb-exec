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
import java.util.Map;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;
import aterm.ATermInt;

public class SSL_hashtable_create extends Primitive {

    protected static Map<Integer, ATermHashtable> hashtables = new HashMap<Integer, ATermHashtable>();

    protected static int counter = 0;

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

        ATermHashtable ath = new ATermHashtable(initialSize, maxLoad);
        int ref = counter;
        hashtables.put(counter++, ath);

        env.setCurrent(env.makeTerm(ref));
        return true;
    }
}

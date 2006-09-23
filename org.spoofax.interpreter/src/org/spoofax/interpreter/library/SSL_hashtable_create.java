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

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

import aterm.ATermInt;

public class SSL_hashtable_create extends Primitive {

    protected static class Hashtable extends HashMap<IStrategoTerm, IStrategoTerm> {

        private static final long serialVersionUID = -8193582031891397734L;

        public Hashtable(int initialSize, int maxLoad) {
            super(initialSize, 1.0f * maxLoad / 100);
        }

    }

    protected SSL_hashtable_create() {
        super("SSL_hashtable_create", 0, 2);
    }

    public boolean call(IContext env, List<Strategy> sargs, List<IStrategoTerm> targs)
            throws InterpreterException {

        if (!(Tools.isTermInt(targs.get(0))))
            return false;
        if (!(Tools.isTermInt(targs.get(1))))
            return false;

        int initialSize = ((ATermInt)targs.get(0)).getInt();
        int maxLoad = ((ATermInt)targs.get(1)).getInt();

        int ref = SSL.registerHashtable(new Hashtable(initialSize, maxLoad));
        
        env.setCurrent(env.getFactory().makeInt(ref));
        return true;
    }
}

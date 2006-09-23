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
import org.spoofax.interpreter.library.SSL_hashtable_create.Hashtable;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_table_hashtable extends Primitive {

    protected SSL_table_hashtable() {
        super("SSL_table_hashtable", 0, 0);
    }
    
    // FIXME: Must be per-interpreter instance, not per-JVM instance //@todo
    protected static Hashtable map;
    private int magicRef = -1;

    static {
        init();
    }

    public static void init() {
        map = new Hashtable(100, 80);
    }

    public boolean call(IContext env, List<Strategy> sargs, List<IStrategoTerm> targs) throws InterpreterException {
        
        if(magicRef == -1) {
            magicRef = SSL.registerHashtable(map);
        }
        
        env.setCurrent(env.getFactory().makeInt(magicRef));
        return true;
    }
}

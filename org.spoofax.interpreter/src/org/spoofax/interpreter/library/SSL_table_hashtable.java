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
import org.spoofax.interpreter.library.SSL_hashtable_create.ATermHashtable;
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;

public class SSL_table_hashtable extends Primitive {

    protected SSL_table_hashtable() {
        super("SSL_table_hashtable", 0, 0);
    }
    
    // FIXME: Must be per-interpreter instance, not per-JVM instance
    protected static ATermHashtable map = new ATermHashtable(100, 80);
    private int magicRef;
    
    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs) throws InterpreterException {
        debug("SSL_table_hashtable");
        
        if(magicRef == -1) {
            magicRef = SSL.registerHashtable(map);
        }
        
        env.setCurrent(env.makeTerm(magicRef));
        return true;
    }
}

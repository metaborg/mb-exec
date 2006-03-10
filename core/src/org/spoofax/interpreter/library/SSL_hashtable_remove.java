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
import org.spoofax.interpreter.library.SSL_hashtable_create.ATermHashtable;
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;
import aterm.ATermInt;

public class SSL_hashtable_remove extends Primitive {

    protected SSL_hashtable_remove() {
        super("SSL_hashtable_remove", 0, 2);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs) throws FatalError {
        debug("SSL_hashtable_remove");
        
        if(!Tools.isATermInt(targs.get(0)))
            return false;
        
        ATermHashtable ath = SSL.getHashtable(Tools.getATermInt((ATermInt)targs.get(0)));
        if(ath == null)
            return false;
        
        ath.remove(targs.get(1));
        env.setCurrent(targs.get(0));
        return true;
    }
}

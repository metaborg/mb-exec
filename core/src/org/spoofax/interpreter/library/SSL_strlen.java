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
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;

public class SSL_strlen extends Primitive {

    protected SSL_strlen() {
        super("SSL_strlen", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs) throws FatalError {
        debug("SSL_strlen");
        
        if(!Tools.isATermString(targs.get(0)))
            return false;
        
        String s = Tools.getATermString(targs.get(0));
        env.setCurrent(env.getFactory().makeInt(s.length()));
        return true;
    }
}

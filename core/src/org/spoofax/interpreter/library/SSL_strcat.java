/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.List;

import org.spoofax.interpreter.Context;
import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.stratego.Strategy;

import aterm.AFun;
import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermInt;
import aterm.ATermList;

public class SSL_strcat extends Primitive {

    protected SSL_strcat() {
        super("SSL_strcat", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs) throws FatalError {
        debug("SSL_strcat");

        if(!Tools.isATermString(targs.get(0)))
            return false;
        if(!Tools.isATermString(targs.get(1)))
            return false;

        String s = Tools.getATermString(targs.get(0));
        String t = Tools.getATermString(targs.get(1));
        
        ATerm r = env.getFactory().make("\"" + s + t + "\"");
        env.setCurrent(r);
        
        return true;
    }
}

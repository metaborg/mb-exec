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

public class SSL_string_to_int extends Primitive {

    protected SSL_string_to_int() {
        super("SSL_string_to_int", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> svars, List<ATerm> tvars) throws FatalError {
        debug("SSL_string_to_int");
        
        if(!Tools.isATermString(tvars.get(0)))
            return false;

        String s = Tools.getATermString(tvars.get(0));
        Integer i = new Integer(s);
        env.setCurrent(env.getFactory().makeInt(i.intValue()));
        return true;
    }
}

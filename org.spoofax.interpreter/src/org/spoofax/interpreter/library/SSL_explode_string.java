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
import aterm.ATermInt;

public class SSL_explode_string extends Primitive {

    protected SSL_explode_string() {
        super("SSL_explode_string", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> svars, List<ATerm> tvars) throws FatalError {
        debug("SSL_explode_string");
        
        ATerm t = tvars.get(0);
        
        if(!Tools.isATermString(t))
            return false;
        
        String s = Tools.getATermString(t);
        ATerm[] r = new ATermInt[s.length()];
        byte[] bs = s.getBytes();
        
        for(int i=0;i<bs.length;i++)
            r[i] = env.getFactory().makeInt(bs[i]);
        
        ATerm sl = env.makeList(r);
        env.setCurrent(sl);
        return true;
    }
}

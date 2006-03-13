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
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;
import aterm.ATermInt;

public class SSL_int_to_string extends Primitive {

    protected SSL_int_to_string() {
        super("SSL_int_to_string", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> svars, List<ATerm> tvars) throws FatalError {
        debug("SSL_int_to_string");
        
        if(tvars.get(0).getType() != ATerm.INT)
            return false;
        
        ATermInt a = (ATermInt) tvars.get(0);
        env.setCurrent(env.makeTerm("\"" + a.getInt() + "\""));
        return true;
    }
}

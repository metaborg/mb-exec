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

public class SSL_is_int extends Primitive {

    protected SSL_is_int() {
        super("SSL_is_int", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> svars, List<ATerm> tvars) throws FatalError {
        debug("SSL_is_int");
        
        return tvars.get(0).getType() == ATerm.INT;
    }
}
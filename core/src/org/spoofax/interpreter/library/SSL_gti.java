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

import aterm.ATerm;
import aterm.ATermInt;
import aterm.ATermList;

public class SSL_gti extends Primitive {

    protected SSL_gti() {
        super("SSL_gti", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs) throws FatalError {
        debug("SSL_gti");
        
        if(targs.get(0).getType() != ATerm.INT)
            return false;
        if(targs.get(1).getType() != ATerm.INT)
            return false;

        ATermInt a = (ATermInt) targs.get(0);
        ATermInt b = (ATermInt) targs.get(1);
        return a.getInt() > b.getInt();
    }
}

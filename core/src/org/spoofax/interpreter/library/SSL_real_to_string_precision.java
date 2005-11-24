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
import aterm.ATermReal;

public class SSL_real_to_string_precision extends Primitive {

    protected SSL_real_to_string_precision() {
        super("SSL_real_to_string_precision", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs) throws FatalError {
        debug("SSL_real_to_string_precision");
        
        if(!Tools.isATermReal(targs.get(0)))
            return false;
        if(!Tools.isATermInt(targs.get(1)))
            return false;

        ATermReal a = (ATermReal) targs.get(0);
        ATermInt b = (ATermInt) targs.get(1);
        String s = String.format("%." + b.getInt() + "f", a.getReal());
        env.setCurrent(env.getFactory().makeString(s));
        return true;
    }
}

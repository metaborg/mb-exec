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

public class SSL_string_to_real extends Primitive {

    protected SSL_string_to_real() {
        super("SSL_string_to_real", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs) throws FatalError {
        debug("SSL_string_to_real");

        if(!Tools.isATermString(targs.get(0)))
            return false;

        Double d = new Double(Tools.getATermString(targs.get(0)));
        env.setCurrent(env.getFactory().makeReal(d.doubleValue()));
        return true;
    }
}
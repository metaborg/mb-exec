/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.List;

import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;
import aterm.ATermReal;

public class SSL_real_to_string extends Primitive {

    protected SSL_real_to_string() {
        super("SSL_real_to_string", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs) throws InterpreterException {
        debug("SSL_real_to_string");

        if(!Tools.isATermReal(targs.get(0)))
            return false;

        ATermReal a = (ATermReal) targs.get(0);
        String s = String.format("%.17g", a.getReal());
        env.setCurrent(env.getFactory().makeString(s));
        return true;
    }
}

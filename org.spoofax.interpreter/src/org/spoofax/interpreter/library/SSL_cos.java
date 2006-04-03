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

public class SSL_cos extends Primitive {

    protected SSL_cos() {
        super("SSL_cos", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs) throws InterpreterException {

        if(!(targs.get(0).getType() == ATerm.REAL))
            return false;

        ATermReal a = (ATermReal) targs.get(0);
        env.setCurrent(env.getFactory().makeReal(Math.cos(a.getReal())));
        return true;
    }
}

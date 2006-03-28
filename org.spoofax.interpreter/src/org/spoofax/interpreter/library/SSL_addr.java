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
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;
import aterm.ATermReal;

public class SSL_addr extends Primitive {

    protected SSL_addr() {
        super("SSL_addr", 0, 2);
    }
    
    public boolean call(IContext env, List<Strategy> svars, List<ATerm> tvars) throws InterpreterException {
        debug("SSL_addr");
        
        if(tvars.get(0).getType() != ATerm.REAL)
            return false;
        if(tvars.get(1).getType() != ATerm.REAL)
            return false;

        ATermReal a = (ATermReal) tvars.get(0);
        ATermReal b = (ATermReal) tvars.get(1);
        env.setCurrent(env.getFactory().makeReal(a.getReal() + b.getReal()));
        return true;
    }
}

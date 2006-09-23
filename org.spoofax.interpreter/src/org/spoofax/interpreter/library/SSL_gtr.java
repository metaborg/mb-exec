/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.List;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

import aterm.ATermReal;

public class SSL_gtr extends Primitive {

    protected SSL_gtr() {
        super("SSL_gtr", 0, 2);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<IStrategoTerm> targs) throws InterpreterException {
        
        if(Tools.isTermReal(targs.get(0)))
            return false;
        if(Tools.isTermReal(targs.get(1)))
            return false;

        ATermReal a = (ATermReal) targs.get(0);
        ATermReal b = (ATermReal) targs.get(1);
        return a.getReal() > b.getReal();
    }
}

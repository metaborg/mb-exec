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
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_subti extends Primitive {

    protected SSL_subti() {
        super("SSL_subti", 0, 2);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<IStrategoTerm> targs) throws InterpreterException {
        
        if(Tools.isTermInt(targs.get(0)))
            return false;
        if(Tools.isTermInt(targs.get(1)))
            return false;

        IStrategoInt a = (IStrategoInt) targs.get(0);
        IStrategoInt b = (IStrategoInt) targs.get(1);
        env.setCurrent(env.getFactory().makeInt(a.getValue() - b.getValue()));
        return true;
    }
}

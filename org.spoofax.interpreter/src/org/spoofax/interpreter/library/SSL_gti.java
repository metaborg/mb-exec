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
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoTerm;

import aterm.ATerm;

public class SSL_gti extends Primitive {

    protected SSL_gti() {
        super("SSL_gti", 0, 2);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<IStrategoTerm> targs) throws InterpreterException {
        
        if(targs.get(0).getTermType() != ATerm.INT)
            return false;
        if(targs.get(1).getTermType() != ATerm.INT)
            return false;

        IStrategoInt a = (IStrategoInt) targs.get(0);
        IStrategoInt b = (IStrategoInt) targs.get(1);
        return a.getValue() > b.getValue();
    }
}

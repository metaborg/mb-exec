/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.util.List;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_mulr extends AbstractPrimitive {

    protected SSL_mulr() {
        super("SSL_mulr", 0, 2);
    }
    
    public boolean call(IContext env, List<IConstruct> sargs, IStrategoTerm[] targs) throws InterpreterException {
        
        if(!Tools.isTermReal(targs[0]))
            return false;
        if(!Tools.isTermReal(targs[1]))
            return false;

        IStrategoReal a = (IStrategoReal) targs[0];
        IStrategoReal b = (IStrategoReal) targs[1];
        env.setCurrent(env.getFactory().makeReal(a.getValue() * b.getValue()));
        return true;
    }
}

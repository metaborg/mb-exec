/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_subtr extends AbstractPrimitive {

    protected SSL_subtr() {
        super("SSL_subtr", 0, 2);
    }
    
    public boolean call(IContext env, IConstruct[] svars, IStrategoTerm[] tvars) throws InterpreterException {
        
        if(!Tools.isTermReal(tvars[0]))
            return false;
        if(!Tools.isTermReal(tvars[1]))
            return false;

        IStrategoReal a = (IStrategoReal) tvars[0];
        IStrategoReal b = (IStrategoReal) tvars[1];
        env.setCurrent(env.getFactory().makeReal(a.getValue() - b.getValue()));
        return true;
    }
}

/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.List;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_rand extends AbstractPrimitive {

    protected SSL_rand() {
        super("SSL_rand", 0, 0);
    }
    
    public boolean call(IContext env, List<IConstruct> sargs, IStrategoTerm[] targs) throws InterpreterException {

        double r = Math.random();
        int i = (int)((r-0.5) * 2147483647);
        
        env.setCurrent(env.getFactory().makeInt(i));
        return true;
    }
}

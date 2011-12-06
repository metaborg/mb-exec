/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_indexedSet_remove extends AbstractPrimitive {

    public SSL_indexedSet_remove() {
        super("SSL_indexedSet_remove", 0, 2);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(targs[0] instanceof StrategoSet))
            return false;
        
        StrategoSet is = (StrategoSet) targs[0];
        if(is == null)
            return false;

        is.remove(targs[1]);
        
        env.setCurrent(targs[0]);
        return true;
    }
}

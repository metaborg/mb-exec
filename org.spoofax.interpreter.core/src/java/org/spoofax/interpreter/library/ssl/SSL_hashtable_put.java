/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.library.ssl.SSL_hashtable_create.Hashtable;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_hashtable_put extends AbstractPrimitive {

    private final SSLLibrary library;

    protected SSL_hashtable_put(SSLLibrary library) {
        super("SSL_hashtable_put", 0, 3);
        this.library = library;
    }
    
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {

        if(!(Tools.isTermInt(targs[0])))
            return false;

        Hashtable ath = library.getHashtable(((IStrategoInt)targs[0]).intValue());
        if(ath == null)
            return false;
        
        ath.put(targs[1], targs[2]);
        env.setCurrent(targs[0]);
        return true;
    }
}

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

public class SSL_hashtable_get extends AbstractPrimitive {

    protected SSL_hashtable_get() {
        super("SSL_hashtable_get", 0, 2);
    }
    
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {

        if(!(Tools.isTermInt(targs[0])))
            return false;

        SSLLibrary or = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        Hashtable ath = or.getHashtable(((IStrategoInt)targs[0]).intValue());
        if(ath == null)
            return false;
        
        IStrategoTerm t = ath.get(targs[1]);
        if(t == null)
            return false;
        
        env.setCurrent(t);
        return true;
    }
}
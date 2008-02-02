/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_hashtable_destroy extends AbstractPrimitive {

    protected SSL_hashtable_destroy() {
        super("SSL_hashtable_destroy", 0, 1);
    }

    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs)
            throws InterpreterException {

        if (!(Tools.isTermInt(targs[0])))
            return false;

        SSLLibrary or = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        boolean res = or.removeHashtable(((IStrategoInt)targs[0]).getValue());
        env.setCurrent(targs[0]);
        return res;
    }
}

/*
 * Created on 11. jan.. 2007
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
import org.spoofax.interpreter.library.ssl.SSL_hashtable_create.Hashtable;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_hashtable_reset extends AbstractPrimitive {

    protected SSL_hashtable_reset() {
        super("SSL_hashtable_reset", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        if (!(Tools.isTermInt(tvars[0])))
            return false;

        SSLLibrary or = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        Hashtable ht = or.getHashtable(((IStrategoInt)tvars[0]).getValue());
        ht.clear();
        return true;
    }

}

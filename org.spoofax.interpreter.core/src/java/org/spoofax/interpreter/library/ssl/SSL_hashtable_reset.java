/*
 * Created on 11. jan.. 2007
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

public class SSL_hashtable_reset extends AbstractPrimitive {

    private final SSLLibrary library;

    protected SSL_hashtable_reset(SSLLibrary library) {
        super("SSL_hashtable_reset", 0, 1);
        this.library = library;
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        if (!(Tools.isTermInt(tvars[0])))
            return false;

        Hashtable ht = library.getHashtable(((IStrategoInt)tvars[0]).intValue());
        ht.clear();
        return true;
    }

}

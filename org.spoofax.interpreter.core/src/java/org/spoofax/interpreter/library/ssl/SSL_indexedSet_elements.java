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
import org.spoofax.interpreter.library.ssl.SSL_indexedSet_create.IndexedSet;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_indexedSet_elements extends AbstractPrimitive {

    protected SSL_indexedSet_elements() {
        super("SSL_indexedSet_elements", 0, 1);
    }

    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs)
            throws InterpreterException {

        if (!(Tools.isTermInt(targs[0])))
            return false;

        IndexedSet is = SSLLibrary.instance(env).getIndexedSet(Tools.asJavaInt(targs[0]));

        if(is == null)
            return false;

        
        env.setCurrent(env.getFactory().makeList(is.keySet()));
        
        return true;
    }
}

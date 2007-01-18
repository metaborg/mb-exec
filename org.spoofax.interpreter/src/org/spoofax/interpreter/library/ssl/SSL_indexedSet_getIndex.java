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
import org.spoofax.interpreter.library.ssl.SSL_indexedSet_create.IndexedSet;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_indexedSet_getIndex extends AbstractPrimitive {

    protected SSL_indexedSet_getIndex() {
        super("SSL_indexedSet_getIndex", 0, 2);
    }

    public boolean call(IContext env, IConstruct[] sargs, IStrategoTerm[] targs)
            throws InterpreterException {

        if (!(Tools.isTermInt(targs[0])))
            return false;

        IndexedSet is = SSLLibrary.instance(env).getIndexedSet(Tools.asJavaInt(targs[0]));
        
        if(is == null)
            return false;
        
        int idx = is.getIndex(targs[1]);
        if(idx == -1)
            return false;
        
        env.setCurrent(env.getFactory().makeInt(idx));
        return true;
    }
}

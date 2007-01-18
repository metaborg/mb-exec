/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.util.HashMap;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_hashtable_create extends AbstractPrimitive {

    protected static class Hashtable extends HashMap<IStrategoTerm, IStrategoTerm> {

        private static final long serialVersionUID = -8193582031891397734L;

        public Hashtable(int initialSize, int maxLoad) {
            super(initialSize, 1.0f * maxLoad / 100);
        }

    }

    protected SSL_hashtable_create() {
        super("SSL_hashtable_create", 0, 2);
    }

    public boolean call(IContext env, IConstruct[] sargs, IStrategoTerm[] targs)
            throws InterpreterException {

        if (!(Tools.isTermInt(targs[0])))
            return false;
        if (!(Tools.isTermInt(targs[1])))
            return false;

        int initialSize = ((IStrategoInt)targs[0]).getValue();
        int maxLoad = ((IStrategoInt)targs[1]).getValue();

        SSLLibrary or = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        int ref = or.registerHashtable(new Hashtable(initialSize, maxLoad));
        
        env.setCurrent(env.getFactory().makeInt(ref));
        return true;
    }
}

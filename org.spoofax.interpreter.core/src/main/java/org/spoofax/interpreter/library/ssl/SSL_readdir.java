/*
 * Created on 9. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.util.TermUtils;

public class SSL_readdir extends AbstractPrimitive {

    protected SSL_readdir() {
        super("SSL_readdir", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!TermUtils.isString(tvars[0]))
            return false;
        
        SSLLibrary or = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        
        String dir = TermUtils.toJavaString(tvars[0]);
        
        String[] entries = or.getIOAgent().readdir(dir);
        if(entries == null)
            return false;
        
        ITermFactory fac = env.getFactory();
        IStrategoTerm[] ts = new IStrategoTerm[entries.length];
        for(int i = 0; i< entries.length; i++)
            ts[i] = fac.makeString(entries[i]);
        
        env.setCurrent(fac.makeList(ts));
        return true;
    }

}

/*
 * Created on 9. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.io.File;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class SSL_readdir extends AbstractPrimitive {

    protected SSL_readdir() {
        super("SSL_readdir", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!Tools.isTermString(tvars[0]))
            return false;
        
        SSLLibrary or = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        
        String dir = Tools.javaString(tvars[0]);
        
        File f = or.getIOAgent().openFile(dir);
        if(f == null)
            return false;
        
        String[] entries = f.list();
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

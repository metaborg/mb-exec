/*
 * Created on 9. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.io.File;
import java.util.List;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class SSL_readdir extends AbstractPrimitive {

    protected SSL_readdir() {
        super("SSL_readdir", 0, 1);
    }

    @Override
    public boolean call(IContext env, List<IConstruct> svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!Tools.isTermString(tvars[0]))
            return false;
        
        String dir = Tools.javaString(tvars[0]);
        
        File f = new File(dir);
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

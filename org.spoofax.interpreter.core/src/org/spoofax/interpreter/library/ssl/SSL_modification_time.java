/*
 * Created on 9. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.io.File;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_modification_time extends AbstractPrimitive {

    SSL_modification_time() {
        super("SSL_modification_time", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!Tools.isTermString(tvars[0]))
            return false;
        
        String fn = Tools.javaString(tvars[0]);
        
        File f = new File(fn);
        if(f == null)
            return false;
        
        // FIXME not good
        env.setCurrent(env.getFactory().makeInt((int)f.lastModified()));
        return true;
    }

}

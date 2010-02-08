/*
 * Created on 11. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.io.IOException;
import java.io.Reader;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_fgetc extends AbstractPrimitive {

    SSL_fgetc() {
        super("SSL_fgetc", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        if(!Tools.isTermInt(tvars[0]))
            return false;
        
        SSLLibrary or = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        Reader in = or.getIOAgent().getReader(Tools.asJavaInt(tvars[0]));

        int r = -1;
        
        try {
            r = in.read();
        } catch(IOException e) {
            throw new InterpreterException(e);
        }
        
        if(r == -1)
            return false;
        
        env.setCurrent(env.getFactory().makeInt(r));
        return true;
        
    }

}

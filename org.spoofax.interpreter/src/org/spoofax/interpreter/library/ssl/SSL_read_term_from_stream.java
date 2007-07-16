/*
 * Created on 11. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.io.IOException;
import java.io.InputStream;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_read_term_from_stream extends AbstractPrimitive {

    SSL_read_term_from_stream() {
        super("SSL_read_term_from_stream", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        // FIXME should we even bother with BAF? Now it's just text
        
        if(!Tools.isTermInt(tvars[0]))
            return false;
        
        SSLLibrary or = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        InputStream is = or.getInputStream(Tools.asJavaInt(tvars[0]));
        if(is == null)
            return false;

        try {
            env.setCurrent(env.getFactory().parseFromStream(is));
        } catch(IOException e) {
            throw new InterpreterException(e);
        }
        return true;
    }

}

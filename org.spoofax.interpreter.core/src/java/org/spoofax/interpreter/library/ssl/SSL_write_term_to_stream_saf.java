/*
 * Created on 11. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.io.IOException;
import java.io.OutputStream;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_write_term_to_stream_saf extends AbstractPrimitive {

    SSL_write_term_to_stream_saf() {
        super("SSL_write_term_to_stream_saf", 0, 2);
        
    }
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        // FIXME SAF? Now it's just text
        
        if(!Tools.isTermInt(tvars[0]))
            return false;
        
        SSLLibrary or = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        OutputStream out = or.getIOAgent().internalGetOutputStream(Tools.asJavaInt(tvars[0]));
        if(out == null)
            return false;
        
        try {
            env.getFactory().unparseToFile(tvars[1], out);
            out.flush();
        } catch(IOException e) {
            throw new InterpreterException(e);
        }
        
        env.setCurrent(tvars[0]);
        return true;
    }

}

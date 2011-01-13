/*
 * Created on 11. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.io.binary.SAFWriter;

public class SSL_write_term_to_stream_saf extends AbstractPrimitive {

    SSL_write_term_to_stream_saf() {
        super("SSL_write_term_to_stream_saf", 0, 2);
        
    }
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] targs)
            throws InterpreterException {
        
        if(!Tools.isTermInt(targs[0]))
            return false;
        
        SSLLibrary or = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        OutputStream out = or.getIOAgent().internalGetOutputStream(Tools.asJavaInt(targs[0]));
        BufferedOutputStream bout = new BufferedOutputStream(out);
        if(out == null)
            return false;
        
        try {
            SAFWriter.writeTermToSAFStream(targs[1], bout);
            bout.close();
            
        } catch(IOException e) {
            throw new InterpreterException(e);
        }
        
        env.setCurrent(targs[0]);
        return true;
    }

}

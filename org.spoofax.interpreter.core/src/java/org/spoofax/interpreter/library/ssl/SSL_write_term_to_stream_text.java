/*
 * Created on 08.aug.2005
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

public class SSL_write_term_to_stream_text extends AbstractPrimitive {

    protected SSL_write_term_to_stream_text() {
        super("SSL_write_term_to_stream_text", 0, 2);
    }
    
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        
        if(!Tools.isTermInt(targs[0]))
            return false;

        SSLLibrary or = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        int fd = Tools.asJavaInt(targs[0]);
        
        OutputStream os = or.getOutputStream(fd);
        if(os == null)
            return false;
        
        try {
            env.getFactory().unparseToFile(targs[1],os);
        } catch(IOException e) {
            throw new InterpreterException(e);
        }
        
        env.setCurrent(targs[0]);
        return true;
    }
}
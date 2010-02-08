/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.io.IOException;
import java.io.Writer;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_write_term_to_stream_taf extends AbstractPrimitive {

    protected SSL_write_term_to_stream_taf() {
        super("SSL_write_term_to_stream_taf", 0, 2);
    }
    
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        
        if(!Tools.isTermInt(targs[0]))
            return false;

        SSLLibrary or = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        int fd = Tools.asJavaInt(targs[0]);
       
        Writer out = or.getIOAgent().getWriter(fd);
        if(out == null)
            return false;
        
        try {
            env.getFactory().unparseToFile(targs[1], out);
        } catch(IOException e) {
            throw new InterpreterException(e);
        }

        env.setCurrent(targs[0]);
        return true;
    }
}

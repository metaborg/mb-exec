/*
 * Created on 11. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_write_term_to_stream_baf extends AbstractPrimitive {

    SSL_write_term_to_stream_baf() {
        super("SSL_write_term_to_stream_baf", 0, 2);
        
    }
    @Override
    public boolean call(IContext env, List<Strategy> svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        // FIXME should we even bother with BAF? Now it's just text
        
        if(!Tools.isTermInt(tvars[0]))
            return false;
        
        SSLLibrary or = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        OutputStream os = or.getOutputStream(Tools.asJavaInt(tvars[0]));
        if(os == null)
            return false;

        System.err.println("warning: Only writing of text ATerms is supported on this platform");
        
        try {
            env.getFactory().unparseToFile(tvars[1], os);
        } catch(IOException e) {
            throw new InterpreterException(e);
        }
        
        env.setCurrent(tvars[0]);
        return true;
    }

}

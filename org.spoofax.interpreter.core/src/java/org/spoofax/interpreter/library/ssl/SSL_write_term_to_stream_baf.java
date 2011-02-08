/*
 * Created on 11. jan.. 2007
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
import org.spoofax.terms.io.binary.TermReader;

public class SSL_write_term_to_stream_baf extends AbstractPrimitive {

    SSL_write_term_to_stream_baf() {
        super("SSL_write_term_to_stream_baf", 0, 2);
        
    }
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] targs)
            throws InterpreterException {
        
        // FIXME should we even bother with BAF? Now it's just text
        
        if(!Tools.isTermInt(targs[0]))
            return false;
        
        SSLLibrary or = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        Writer out = or.getIOAgent().getWriter(Tools.asJavaInt(targs[0]));
        if(out == null)
            return false;
        
        try {
            new TermReader(env.getFactory()).unparseToFile(targs[1], out);
            out.flush();
        } catch(IOException e) {
            throw new InterpreterException(e);
        }
        
        env.setCurrent(targs[0]);
        return true;
    }

}

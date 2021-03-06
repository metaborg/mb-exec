/*
 * Created on 11. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.ssl;

import java.io.IOException;
import java.io.Writer;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.util.TermUtils;

public class SSL_fflush extends AbstractPrimitive {

    SSL_fflush() {
        super("SSL_fflush", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

        if(!TermUtils.isInt(tvars[0]))
            return false;
        
        SSLLibrary or = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        Writer writer = or.getIOAgent().getWriter(TermUtils.toJavaInt(tvars[0]));
        if (writer == null) return false;
        
        try {
            writer.flush();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}

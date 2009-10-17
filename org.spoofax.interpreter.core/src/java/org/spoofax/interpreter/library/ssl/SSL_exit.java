/*
 * Created on 15. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.InterpreterExit;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_exit extends AbstractPrimitive {

    public SSL_exit() {
        super("SSL_exit", 0, 1);
    }
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!Tools.isTermInt(tvars[0]))
            return false;
        
        SSLLibrary.instance(env).getIOAgent().closeAllFiles();
        
        int exitCode = Tools.asJavaInt(tvars[0]);
        env.getStackTracer().popOnExit(exitCode == 0);
        
        throw new InterpreterExit(exitCode);
    }

}

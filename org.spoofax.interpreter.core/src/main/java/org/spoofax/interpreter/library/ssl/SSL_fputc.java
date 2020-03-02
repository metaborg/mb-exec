/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.ssl;

import java.io.IOException;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.util.TermUtils;

public class SSL_fputc extends AbstractPrimitive {

    protected SSL_fputc() {
        super("SSL_fputc", 0, 2);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {

        if(!(TermUtils.isInt(targs[0])))
            return false;
        if(!(TermUtils.isInt(targs[1])))
            return false;

        SSLLibrary or = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        
        IOAgent agent = or.getIOAgent();
        
        try {
            agent.writeChar(TermUtils.toJavaInt(targs[1]), TermUtils.toJavaInt(targs[0]));
        } catch(IOException e) {
            throw new InterpreterException(e);
        }
        
        env.setCurrent(targs[1]);
        return true;
    }
}

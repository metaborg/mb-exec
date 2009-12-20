/*
 * Created on 11. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_fopen extends AbstractPrimitive {

    public static final String NAME = "SSL_fopen";
    
    protected SSL_fopen() {
        super(NAME, 0, 2);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!Tools.isTermString(tvars[0]))
            return false;
        if(!Tools.isTermString(tvars[1]))
            return false;
        
        String fn = Tools.javaString(tvars[0]);
        String mode = Tools.javaString(tvars[1]);
        
        IStrategoInt result = call(env, fn, mode);
        if (result == null) return false;
        
        env.setCurrent(result);
        return true;
    }

    protected IStrategoInt call(IContext env, String fn, String mode) {
        SSLLibrary op = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        try {
            int ref = op.getIOAgent().openRandomAccessFile(fn, mode);
            return env.getFactory().makeInt(ref);
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

}

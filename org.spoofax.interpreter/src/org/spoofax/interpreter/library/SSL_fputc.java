/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_fputc extends Primitive {

    protected SSL_fputc() {
        super("SSL_fputc", 0, 2);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, IStrategoTerm[] targs) throws InterpreterException {

        if(!(Tools.isTermInt(targs[0])))
            return false;
        if(!(Tools.isTermInt(targs[1])))
            return false;

        SSL or = (SSL) env.getOperatorRegistry(SSL.REGISTRY_NAME);
        
        OutputStream s = or.outputStreamFromTerm((IStrategoInt)targs[1]);
        
        try {
            s.write(((IStrategoInt)targs[0]).getValue());
        } catch(IOException e) {
            throw new InterpreterException(e);
        }
        
        return true;
    }
}

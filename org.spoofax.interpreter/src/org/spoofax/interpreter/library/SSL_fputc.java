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

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;
import aterm.ATermInt;

public class SSL_fputc extends Primitive {

    protected SSL_fputc() {
        super("SSL_fputc", 0, 2);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs) throws FatalError {
        debug("SSL_fputc");
        
        if(!Tools.isATermInt(targs.get(0)))
            return false;
        if(!Tools.isATermInt(targs.get(1)))
            return false;

        OutputStream s = SSL.outputStreamFromTerm((ATermInt)targs.get(1));
        try {
            s.write(Tools.getATermInt((ATermInt)targs.get(0)));
        } catch(IOException e) {
            throw new FatalError(e);
        }
        
        return true;
    }
}

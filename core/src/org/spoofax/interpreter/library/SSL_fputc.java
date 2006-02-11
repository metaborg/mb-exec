/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

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

        int c = Tools.getATermInt((ATermInt)targs.get(0));
        int sid = Tools.getATermInt((ATermInt)targs.get(1));
        
        if(sid == SSL.CONST_STDERR)
            System.err.print(c);
        else
            // FIXME: Handle all kinds of streams
            throw new FatalError("Stream unknown");
        
        return true;
    }
}

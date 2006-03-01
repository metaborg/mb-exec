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
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;
import aterm.ATermInt;

public class SSL_write_term_to_stream_text extends Primitive {

    protected SSL_write_term_to_stream_text() {
        super("SSL_write_term_to_stream_text", 0, 2);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs) throws FatalError {
        debug("SSL_write_term_to_stream_text");
        
        if(targs.get(0).getType() != ATerm.INT)
            return false;

        OutputStream ous = SSL.outputStreamFromTerm((ATermInt)targs.get(0));

        ATerm t = targs.get(1);
        
        try {
            t.writeToTextFile(ous);
        } catch (IOException e) {
            throw new FatalError(e);
        }
        return true;
    }
}

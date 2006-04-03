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

import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;
import aterm.ATermInt;

public class SSL_fputs extends Primitive {

    protected SSL_fputs() {
        super("SSL_fputs", 0, 2);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs) throws InterpreterException {
        
        if(!Tools.isATermString(targs.get(0)))
            return false;
        if(!(targs.get(1).getType() == ATerm.INT))
            return false;

        OutputStream ous = SSL.outputStreamFromTerm((ATermInt)targs.get(1));
        try {
            ous.write(Tools.getATermString(targs.get(0)).getBytes());
        } catch(IOException e) {
            throw new InterpreterException(e);
        }
        
        return true;
    }
}

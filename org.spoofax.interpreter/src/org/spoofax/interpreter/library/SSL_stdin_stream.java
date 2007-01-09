/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.List;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_stdin_stream extends AbstractPrimitive {

    protected SSL_stdin_stream() {
        super("SSL_stdin_stream", 0, 0);
    }
    
    public boolean call(IContext env, List<IConstruct> sargs, IStrategoTerm[] targs) throws InterpreterException {
        
        env.setCurrent(env.getFactory().makeInt(SSLLibrary.CONST_STDIN));
        return true;
    }
}

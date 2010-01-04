/*
 * Created on 11. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import static org.spoofax.interpreter.core.Tools.*;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_perror extends AbstractPrimitive {

    SSL_perror() {
        super("SSL_perror", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        String message = tvars[0].getTermType() == IStrategoTerm.STRING
            ? asJavaString(tvars[0])
            : "(no details on this error; perror not supported)";
        
        System.err.println("ERROR: " + message);
        return true;
    }

}

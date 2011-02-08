/*
 * Created on 11. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.ParseError;

public class SSL_read_term_from_string extends AbstractPrimitive {
    
    private static final String NAME = "SSL_read_term_from_string";

    public SSL_read_term_from_string() {
        super(NAME, 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

        if(!Tools.isTermString(tvars[0]))
            return false;
        
        try {
            IStrategoTerm t = env.getFactory().parseFromString(Tools.javaString(tvars[0]));
            env.setCurrent(t);
        } catch (ParseError e) {
            SSLLibrary.instance(env).getIOAgent().printError(NAME + ": " + e.getMessage());
            return false;
        }
        return true;
    }

}

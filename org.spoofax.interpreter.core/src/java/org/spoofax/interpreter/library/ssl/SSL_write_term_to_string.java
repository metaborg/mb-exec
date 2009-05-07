/*
 * Created on 9. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.InlinePrinter;

public class SSL_write_term_to_string extends AbstractPrimitive {
    
    private final InlinePrinter printer = new InlinePrinter();

    protected SSL_write_term_to_string() {
        super("SSL_write_term_to_string", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        tvars[0].prettyPrint(printer);
        String result = printer.toString();
        printer.reset();
        env.setCurrent(env.getFactory().makeString(result));
        return true;
    }

}

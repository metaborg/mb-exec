package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

/**
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class SSL_TicksToSeconds extends AbstractPrimitive {

    protected SSL_TicksToSeconds() {
        super("SSL_TicksToSeconds", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if (tvars[0].getTermType() != IStrategoTerm.INT)
            return false;
        
        env.setCurrent(tvars[0]);
        return true;
    }

}

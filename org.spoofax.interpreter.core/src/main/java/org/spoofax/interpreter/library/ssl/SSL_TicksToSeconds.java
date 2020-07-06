package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.TermType;
import org.spoofax.terms.util.TermUtils;

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
        
        IStrategoTerm time = tvars[0];
        int timeValue; 
        
        if (time.getType() == TermType.REAL)
            timeValue = (int) ((IStrategoReal) time).realValue();
        else if (TermUtils.isInt(time))
            timeValue = ((IStrategoInt) time).intValue();
        else
            return false;
        
        env.setCurrent(env.getFactory().makeReal(timeValue / (double) SSL_times.TICKS_PER_SECOND));
        return true;
    }

}

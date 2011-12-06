package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_divr extends AbstractPrimitive {

    protected SSL_divr() {
        super("SSL_divr", 0, 2);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) throws InterpreterException {
        double a, b;
        
        // TODO: Factor out syntactic noise
        // TODO: Apply this pattern to other primitives; many (all?) real operations also work on ints
        
        if(Tools.isTermReal(tvars[0])) {
            a = ((IStrategoReal) tvars[0]).realValue();     
        } else if (Tools.isTermInt(tvars[0])) {
            a = ((IStrategoInt) tvars[0]).intValue();
        } else {
            return false;
        }
        
        if(Tools.isTermReal(tvars[1])) {
            b = ((IStrategoReal) tvars[1]).realValue();
        } else if (Tools.isTermInt(tvars[1])) {
            b = ((IStrategoInt) tvars[1]).intValue();
        } else {
            return false;
        }

        env.setCurrent(env.getFactory().makeReal(a / b));
        return true;
    }
}

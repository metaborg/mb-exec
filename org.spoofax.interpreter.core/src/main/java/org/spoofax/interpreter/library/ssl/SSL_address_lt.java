package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_address_lt extends AbstractPrimitive {

    protected SSL_address_lt() {
        super("SSL_address_lt", 0, 2);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) throws InterpreterException {
        if(System.identityHashCode(tvars[0]) < System.identityHashCode(tvars[1])) {
            return true;
        } else {
            return false;
        }
    }
}

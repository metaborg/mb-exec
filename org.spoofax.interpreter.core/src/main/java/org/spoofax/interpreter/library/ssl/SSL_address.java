package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_address extends AbstractPrimitive {

	protected SSL_address() {
		super("SSL_address", 0, 1);
	}

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) throws InterpreterException {
        env.setCurrent(env.getFactory().makeString(Integer.toString(System.identityHashCode(tvars[0]))));
        return true;
    }
}

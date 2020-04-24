package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_map_subtract extends SSL_immutable_map_subtract_eq {

    protected SSL_immutable_map_subtract() {
        super("SSL_immutable_map_subtract", 0, 1);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        return subtract(env, targs, Object::equals);
    }
}

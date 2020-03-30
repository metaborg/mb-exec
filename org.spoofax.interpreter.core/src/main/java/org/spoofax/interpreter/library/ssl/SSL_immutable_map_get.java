package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_map_get extends SSL_immutable_map_get_eq {

    protected SSL_immutable_map_get() {
        super("SSL_immutable_map_get", 0, 1);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        return get(env, targs, Object::equals);
    }
}

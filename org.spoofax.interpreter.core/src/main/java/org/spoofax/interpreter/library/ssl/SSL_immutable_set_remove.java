package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_set_remove extends SSL_immutable_set_remove_eq {

    protected SSL_immutable_set_remove() {
        super("SSL_immutable_set_remove", 0, 1);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        return remove(env, targs, Object::equals);
    }
}

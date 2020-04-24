package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_set_strict_subset extends SSL_immutable_set_strict_subset_eq {

    protected SSL_immutable_set_strict_subset() {
        super("SSL_immutable_set_strict_subset", 0, 1);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        return strictSubset(env, targs, Object::equals);
    }
}

package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_map extends AbstractPrimitive {

    protected SSL_immutable_map() {
        super("SSL_immutable_map", 0, 0);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        env.setCurrent(new StrategoImmutableMap());
        return true;
    }
}

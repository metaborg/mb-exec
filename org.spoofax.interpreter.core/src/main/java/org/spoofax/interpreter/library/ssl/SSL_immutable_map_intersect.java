package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_map_intersect extends SSL_immutable_map_intersect_eq {

    protected SSL_immutable_map_intersect() {
        super("SSL_immutable_map_intersect", 1, 1);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        return intersect(env, sargs, targs, Object::equals);
    }
}

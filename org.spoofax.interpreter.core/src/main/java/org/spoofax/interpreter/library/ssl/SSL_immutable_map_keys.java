package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_map_keys extends AbstractPrimitive {

    protected SSL_immutable_map_keys() {
        super("SSL_immutable_map_keys", 0, 0);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }

        final StrategoImmutableMap map = (StrategoImmutableMap) env.current();

        env.setCurrent(env.getFactory().makeList(map.backingMap.keySet()));
        return true;
    }
}

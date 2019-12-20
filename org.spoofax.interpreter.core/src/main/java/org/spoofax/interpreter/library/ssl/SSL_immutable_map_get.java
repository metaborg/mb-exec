package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_map_get extends AbstractPrimitive {

    protected SSL_immutable_map_get() {
        super("SSL_immutable_map_get", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs)
            throws InterpreterException {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }

        final StrategoImmutableMap map = (StrategoImmutableMap) env.current();
        final IStrategoTerm key = targs[0];

        final IStrategoTerm newCurrent = map.backingMap.get(key);
        if(newCurrent == null) {
            return false;
        }
        env.setCurrent(newCurrent);
        return true;
    }
}

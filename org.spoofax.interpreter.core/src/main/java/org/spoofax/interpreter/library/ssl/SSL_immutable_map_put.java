package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_map_put extends AbstractPrimitive {

    protected SSL_immutable_map_put() {
        super("SSL_immutable_map_put", 0, 2);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs)
            throws InterpreterException {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }

        final StrategoImmutableMap map = (StrategoImmutableMap) env.current();
        final IStrategoTerm key = targs[0];
        final IStrategoTerm value = targs[1];

        env.setCurrent(new StrategoImmutableMap(map.backingMap.__put(key, value)));
        return true;
    }
}

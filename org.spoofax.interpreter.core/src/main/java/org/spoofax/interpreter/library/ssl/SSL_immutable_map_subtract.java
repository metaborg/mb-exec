package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_map_subtract extends AbstractPrimitive {

    protected SSL_immutable_map_subtract() {
        super("SSL_immutable_map_subtract", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs)
            throws InterpreterException {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }
        if(!(targs[0] instanceof StrategoImmutableMap)) {
            return false;
        }

        final Map.Transient<IStrategoTerm, IStrategoTerm> one = ((StrategoImmutableMap) env.current()).backingMap.asTransient();
        for(IStrategoTerm key : ((StrategoImmutableMap) targs[0]).backingMap.keySet()) {
            one.__remove(key);
        }

        env.setCurrent(new StrategoImmutableMap(one.freeze()));
        return true;
    }
}

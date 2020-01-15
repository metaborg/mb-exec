package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.util.EqualityComparator;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_map_get_eq extends AbstractPrimitive {

    protected SSL_immutable_map_get_eq() {
        this("SSL_immutable_map_get_eq", 1, 1);
    }

    public SSL_immutable_map_get_eq(String name, int svars, int targs) {
        super(name, svars, targs);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        return get(env, targs, new StrategyEqualityComparator(env, sargs[0]));
    }

    protected boolean get(IContext env, IStrategoTerm[] targs, EqualityComparator<Object> cmp) {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }

        final StrategoImmutableMap map = (StrategoImmutableMap) env.current();
        final IStrategoTerm key = targs[0];

        final IStrategoTerm newCurrent = map.backingMap.getEquivalent(key, cmp);
        if(newCurrent == null) {
            return false;
        }
        env.setCurrent(newCurrent);
        return true;
    }
}

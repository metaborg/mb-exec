package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.util.EqualityComparator;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_map_put_eq extends AbstractPrimitive {

    protected SSL_immutable_map_put_eq() {
        this("SSL_immutable_map_put_eq", 1, 2);
    }

    public SSL_immutable_map_put_eq(String name, int svars, int tvars) {
        super(name, svars, tvars);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        return put(env, targs, new StrategyEqualityComparator(env, sargs[0]));
    }

    protected boolean put(IContext env, IStrategoTerm[] targs, EqualityComparator<Object> cmp) {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }

        final StrategoImmutableMap map = (StrategoImmutableMap) env.current();
        final IStrategoTerm key = targs[0];
        final IStrategoTerm value = targs[1];

        env.setCurrent(new StrategoImmutableMap(map.backingMap.__putEquivalent(key, value, cmp)));
        return true;
    }
}

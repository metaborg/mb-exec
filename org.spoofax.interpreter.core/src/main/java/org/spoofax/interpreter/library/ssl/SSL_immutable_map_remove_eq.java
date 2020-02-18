package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.util.EqualityComparator;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_map_remove_eq extends AbstractPrimitive {

    protected SSL_immutable_map_remove_eq() {
        this("SSL_immutable_map_remove_eq", 1, 1);
    }

    public SSL_immutable_map_remove_eq(String name, int svars, int tvars) {
        super(name, svars, tvars);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        return remove(env, targs, new StrategyEqualityComparator(env, sargs[0]));
    }

    protected boolean remove(IContext env, IStrategoTerm[] targs, EqualityComparator<Object> cmp) {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }

        final StrategoImmutableMap map = (StrategoImmutableMap) env.current();
        final IStrategoTerm key = targs[0];

        env.setCurrent(new StrategoImmutableMap(map.backingMap.__removeEquivalent(key, cmp)));
        return true;
    }
}

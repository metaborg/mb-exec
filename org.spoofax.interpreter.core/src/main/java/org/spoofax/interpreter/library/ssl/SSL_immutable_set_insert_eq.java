package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.util.EqualityComparator;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_set_insert_eq extends AbstractPrimitive {

    protected SSL_immutable_set_insert_eq() {
        this("SSL_immutable_set_insert_eq", 1, 1);
    }

    public SSL_immutable_set_insert_eq(String name, int svars, int tvars) {
        super(name, svars, tvars);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        return insert(env, targs, new StrategyEqualityComparator(env, sargs[0]));
    }

    protected boolean insert(IContext env, IStrategoTerm[] targs, EqualityComparator<Object> cmp) {
        if(!(env.current() instanceof StrategoImmutableSet)) {
            return false;
        }

        final StrategoImmutableSet set = (StrategoImmutableSet) env.current();
        final IStrategoTerm value = targs[0];

        env.setCurrent(new StrategoImmutableSet(set.backingSet.__insertEquivalent(value, cmp)));
        return true;
    }
}

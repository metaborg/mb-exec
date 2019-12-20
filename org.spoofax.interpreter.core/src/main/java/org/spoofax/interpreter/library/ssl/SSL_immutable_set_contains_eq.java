package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.util.EqualityComparator;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_set_contains_eq extends AbstractPrimitive {

    protected SSL_immutable_set_contains_eq() {
        this("SSL_immutable_set_contains_eq", 1, 1);
    }

    public SSL_immutable_set_contains_eq(String name, int svars, int tvars) {
        super(name, svars, tvars);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        return contains(env, targs, new StrategyEqualityComparator(env, sargs[0]));
    }

    protected boolean contains(IContext env, IStrategoTerm[] targs, EqualityComparator<Object> cmp) {
        if(!(env.current() instanceof StrategoImmutableSet)) {
            return false;
        }

        final StrategoImmutableSet set = (StrategoImmutableSet) env.current();
        final IStrategoTerm key = targs[0];

        return set.backingSet.containsEquivalent(key, cmp);
    }
}

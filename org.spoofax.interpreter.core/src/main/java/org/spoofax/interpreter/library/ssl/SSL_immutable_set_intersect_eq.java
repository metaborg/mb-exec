package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Set;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_set_intersect_eq extends AbstractPrimitive {

    protected SSL_immutable_set_intersect_eq() {
        super("SSL_immutable_set_intersect_eq", 1, 1);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableSet)) {
            return false;
        }
        if(!(targs[0] instanceof StrategoImmutableSet)) {
            return false;
        }
        final Strategy comp = sargs[0];

        final Set.Immutable<IStrategoTerm> one = ((StrategoImmutableSet) env.current()).backingSet;
        final Set.Transient<IStrategoTerm> other = ((StrategoImmutableSet) targs[0]).backingSet.asTransient();

        env.setCurrent(
            new StrategoImmutableSet(one.__retainAllEquivalent(other, new StrategyEqualityComparator(env, comp))));
        return true;
    }
}

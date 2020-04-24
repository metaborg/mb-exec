package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Set;
import io.usethesource.capsule.util.EqualityComparator;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_set_strict_subset_eq extends AbstractPrimitive {

    protected SSL_immutable_set_strict_subset_eq() {
        super("SSL_immutable_set_strict_subset", 1, 1);
    }

    public SSL_immutable_set_strict_subset_eq(String name, int svars, int tvars) {
        super(name, svars, tvars);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        return strictSubset(env, targs, new StrategyEqualityComparator(env, sargs[0]));
    }

    protected boolean strictSubset(IContext env, IStrategoTerm[] targs, EqualityComparator<Object> cmp) {
        if(!(env.current() instanceof StrategoImmutableSet)) {
            return false;
        }
        if(!(targs[0] instanceof StrategoImmutableSet)) {
            return false;
        }

        final Set.Immutable<IStrategoTerm> left = ((StrategoImmutableSet) env.current()).backingSet;
        final Set.Immutable<IStrategoTerm> right = ((StrategoImmutableSet) targs[0]).backingSet;
        if(left.size() >= right.size()) {
            return false;
        }

        for(IStrategoTerm elem : left) {
            if(!right.containsEquivalent(elem, cmp)) {
                return false;
            }
        }

        return true;
    }
}

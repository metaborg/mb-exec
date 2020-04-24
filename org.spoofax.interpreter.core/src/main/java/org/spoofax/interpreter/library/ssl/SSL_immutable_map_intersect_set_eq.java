package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;
import io.usethesource.capsule.Set;
import io.usethesource.capsule.util.EqualityComparator;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import java.util.Iterator;

public class SSL_immutable_map_intersect_set_eq extends AbstractPrimitive {

    protected SSL_immutable_map_intersect_set_eq() {
        this("SSL_immutable_map_intersect_set_eq", 1, 1);
    }

    public SSL_immutable_map_intersect_set_eq(String name, int svars, int tvars) {
        super(name, svars, tvars);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        return intersect_set(env, targs, new StrategyEqualityComparator(env, sargs[0]));
    }

    protected boolean intersect_set(IContext env, IStrategoTerm[] targs, EqualityComparator<Object> cmp) {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }
        if(!(targs[0] instanceof StrategoImmutableSet)) {
            return false;
        }

        final Map.Transient<IStrategoTerm, IStrategoTerm> one =
            ((StrategoImmutableMap) env.current()).backingMap.asTransient();
        final Set.Immutable<IStrategoTerm> other = ((StrategoImmutableSet) targs[0]).backingSet;
        for(Iterator<IStrategoTerm> iterator = one.keyIterator(); iterator.hasNext(); ) {
            IStrategoTerm key = iterator.next();
            if(!other.containsEquivalent(key, cmp)) {
                iterator.remove();
            }
        }

        env.setCurrent(new StrategoImmutableMap(one.freeze()));
        return true;
    }
}

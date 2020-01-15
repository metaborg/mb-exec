package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;
import io.usethesource.capsule.util.EqualityComparator;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_map_subtract_set_eq extends AbstractPrimitive {

    protected SSL_immutable_map_subtract_set_eq() {
        this("SSL_immutable_map_subtract_set_eq", 1, 1);
    }

    public SSL_immutable_map_subtract_set_eq(String name, int svars, int tvars) {
        super(name, svars, tvars);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        return subtract(env, targs, new StrategyEqualityComparator(env, sargs[0]));
    }

    protected boolean subtract(IContext env, IStrategoTerm[] targs, EqualityComparator<Object> cmp) {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }
        if(!(targs[0] instanceof StrategoImmutableSet)) {
            return false;
        }

        final Map.Transient<IStrategoTerm, IStrategoTerm> one =
            ((StrategoImmutableMap) env.current()).backingMap.asTransient();
        for(IStrategoTerm key : ((StrategoImmutableSet) targs[0]).backingSet) {
            one.__removeEquivalent(key, cmp);
        }

        env.setCurrent(new StrategoImmutableMap(one.freeze()));
        return true;
    }
}

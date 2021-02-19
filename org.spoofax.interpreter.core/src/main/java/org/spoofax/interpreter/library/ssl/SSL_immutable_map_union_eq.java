package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;
import io.usethesource.capsule.util.EqualityComparator;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_map_union_eq extends AbstractPrimitive {

    protected SSL_immutable_map_union_eq() {
        this("SSL_immutable_map_union_eq", 2, 1);
    }

    public SSL_immutable_map_union_eq(String name, int svars, int tvars) {
        super(name, svars, tvars);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        return union(env, sargs, targs, new StrategyEqualityComparator(env, sargs[1]));
    }

    protected boolean union(IContext env, Strategy[] sargs, IStrategoTerm[] targs, EqualityComparator<Object> cmp)
        throws InterpreterException {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }
        if(!(targs[0] instanceof StrategoImmutableMap)) {
            return false;
        }
        final Strategy merge = sargs[0];

        final Map.Transient<IStrategoTerm, IStrategoTerm> one =
            ((StrategoImmutableMap) env.current()).backingMap.asTransient();
        final Map.Immutable<IStrategoTerm, IStrategoTerm> other = ((StrategoImmutableMap) targs[0]).backingMap;
        if(one.isEmpty()) {
            env.setCurrent(targs[0]);
            return true;
        }
        for(java.util.Map.Entry<IStrategoTerm, IStrategoTerm> e : other.entrySet()) {
            if(one.containsKeyEquivalent(e.getKey(), cmp)) {
                final IStrategoTerm left = one.get(e.getKey());
                final IStrategoTerm right = e.getValue();
                env.setCurrent(env.getFactory().makeTuple(left, right));
                if(!merge.evaluate(env)) {
                    return false;
                }
                one.__put(e.getKey(), env.current());
            } else {
                one.__put(e.getKey(), e.getValue());
            }
        }

        env.setCurrent(new StrategoImmutableMap(one.freeze()));
        return true;
    }
}

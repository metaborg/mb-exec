package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;
import io.usethesource.capsule.util.EqualityComparator;

import org.metaborg.util.collection.CapsuleUtil;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_map_intersect_eq extends AbstractPrimitive {

    protected SSL_immutable_map_intersect_eq() {
        this("SSL_immutable_map_intersect_eq", 2, 1);
    }

    @SuppressWarnings("SameParameterValue")
    protected SSL_immutable_map_intersect_eq(String name, int svars, int tvars) {
        super(name, svars, tvars);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        return intersect(env, sargs, targs, new InterpretedStrategyEqualityComparator(env, sargs[1]));
    }

    protected boolean intersect(IContext env, Strategy[] sargs, IStrategoTerm[] targs, EqualityComparator<Object> cmp)
        throws InterpreterException {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }
        if(!(targs[0] instanceof StrategoImmutableMap)) {
            return false;
        }

        final Map.Transient<IStrategoTerm, IStrategoTerm> intersection = CapsuleUtil.transientMap();
        final Map.Immutable<IStrategoTerm, IStrategoTerm> one = ((StrategoImmutableMap) env.current()).backingMap;
        final Map.Immutable<IStrategoTerm, IStrategoTerm> other = ((StrategoImmutableMap) targs[0]).backingMap;
        for(java.util.Map.Entry<IStrategoTerm, IStrategoTerm> e : other.entrySet()) {
            if(one.containsKeyEquivalent(e.getKey(), cmp)) {
                final IStrategoTerm left = one.get(e.getKey());
                final IStrategoTerm right = e.getValue();
                env.setCurrent(env.getFactory().makeTuple(left, right));
                if(!sargs[0].evaluate(env)) {
                    return false;
                }
                intersection.__put(e.getKey(), env.current());
            }
        }

        env.setCurrent(new StrategoImmutableMap(intersection.freeze()));
        return true;
    }
}

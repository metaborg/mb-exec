package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_map_intersect extends AbstractPrimitive {

    protected SSL_immutable_map_intersect() {
        super("SSL_immutable_map_intersect", 1, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs)
            throws InterpreterException {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }
        if(!(targs[0] instanceof StrategoImmutableMap)) {
            return false;
        }

        final Map.Transient<IStrategoTerm, IStrategoTerm> intersection = Map.Transient.of();
        final Map.Immutable<IStrategoTerm, IStrategoTerm> one = ((StrategoImmutableMap) env.current()).backingMap;
        final Map.Immutable<IStrategoTerm, IStrategoTerm> other = ((StrategoImmutableMap) targs[0]).backingMap;
        for(java.util.Map.Entry<IStrategoTerm, IStrategoTerm> e : other.entrySet()) {
            if(one.containsKey(e.getKey())) {
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

package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_map_filter extends AbstractPrimitive {

    protected SSL_immutable_map_filter() {
        super("SSL_immutable_map_filter", 1, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs)
            throws InterpreterException {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }

        final Map.Immutable<IStrategoTerm, IStrategoTerm> map = ((StrategoImmutableMap) env.current()).backingMap;
        final Map.Transient<IStrategoTerm, IStrategoTerm> resultMap = Map.Transient.of();
        for(java.util.Map.Entry<IStrategoTerm, IStrategoTerm> e : map.entrySet()) {
            env.setCurrent(env.getFactory().makeTuple(e.getKey(), e.getValue()));
            if(!sargs[0].evaluate(env)) {
                continue;
            }
            final IStrategoTerm current = env.current();
            if(!(Tools.isTermTuple(current)) && current.getSubtermCount() == 2) {
                return false;
            }
            resultMap.__put(current.getSubterm(0), current.getSubterm(1));
        }

        env.setCurrent(new StrategoImmutableMap(resultMap.freeze()));
        return true;
    }
}

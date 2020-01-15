package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class SSL_immutable_map_filter extends AbstractPrimitive {

    protected SSL_immutable_map_filter() {
        super("SSL_immutable_map_filter", 2, 0);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }
        final Strategy mapping = sargs[0];
        final Strategy merge = sargs[1];
        final ITermFactory f = env.getFactory();

        final Map.Immutable<IStrategoTerm, IStrategoTerm> map = ((StrategoImmutableMap) env.current()).backingMap;
        final Map.Transient<IStrategoTerm, IStrategoTerm> resultMap = Map.Transient.of();
        for(java.util.Map.Entry<IStrategoTerm, IStrategoTerm> e : map.entrySet()) {
            env.setCurrent(f.makeTuple(e.getKey(), e.getValue()));
            if(!mapping.evaluate(env)) {
                continue;
            }
            final IStrategoTerm current = env.current();
            if(!(Tools.isTermTuple(current)) && current.getSubtermCount() == 2) {
                return false;
            }
            final IStrategoTerm newKey = current.getSubterm(0);
            final IStrategoTerm newValue = current.getSubterm(1);
            if(resultMap.containsKey(newKey)) {
                final IStrategoTerm oldValue = resultMap.get(newKey);
                env.setCurrent(f.makeTuple(newKey, f.makeTuple(oldValue, newValue)));
                if(!merge.evaluate(env)) {
                    return false;
                }
                resultMap.__put(newKey, env.current());
            } else {
                resultMap.__put(newKey, newValue);
            }
        }

        env.setCurrent(new StrategoImmutableMap(resultMap.freeze()));
        return true;
    }
}

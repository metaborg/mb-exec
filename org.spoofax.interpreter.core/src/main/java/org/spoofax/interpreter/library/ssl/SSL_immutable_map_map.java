package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_map_map extends AbstractPrimitive {

    protected SSL_immutable_map_map() {
        super("SSL_immutable_map_map", 2, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs)
            throws InterpreterException {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }
        final Strategy mapping = sargs[0];
        final Strategy merge = sargs[1];

        final Map.Immutable<IStrategoTerm, IStrategoTerm> map = ((StrategoImmutableMap) env.current()).backingMap;
        final Map.Transient<IStrategoTerm, IStrategoTerm> resultMap = Map.Transient.of();
        for(java.util.Map.Entry<IStrategoTerm, IStrategoTerm> e : map.entrySet()) {
            env.setCurrent(env.getFactory().makeTuple(e.getKey(), e.getValue()));
            if(!mapping.evaluate(env)) {
                return false;
            }
            final IStrategoTerm current = env.current();
            if(!(Tools.isTermTuple(current)) && current.getSubtermCount() == 2) {
                return false;
            }
            final IStrategoTerm newKey = current.getSubterm(0);
            final IStrategoTerm newValue = current.getSubterm(1);
            final IStrategoTerm oldValue = resultMap.__put(newKey, newValue);
            if(oldValue != null) {
                env.setCurrent(env.getFactory().makeTuple(oldValue, newValue));
                if(!merge.evaluate(env)) {
                    return false;
                }
                resultMap.__put(newKey, env.current());
            }
        }

        env.setCurrent(new StrategoImmutableMap(resultMap.freeze()));
        return true;
    }
}

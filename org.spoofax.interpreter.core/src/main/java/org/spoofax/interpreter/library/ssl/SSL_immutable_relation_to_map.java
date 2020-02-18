package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.BinaryRelation;
import io.usethesource.capsule.Map;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import java.util.Map.Entry;

public class SSL_immutable_relation_to_map extends AbstractPrimitive {

    protected SSL_immutable_relation_to_map() {
        super("SSL_immutable_relation_to_map", 1, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        if(!Tools.isTermList(env.current())) {
            return false;
        }
        final Strategy merge = sargs[0];
        final ITermFactory f = env.getFactory();

        final BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> relation =
            ((StrategoImmutableRelation) env.current()).backingRelation;
        final Map.Transient<IStrategoTerm, IStrategoTerm> result = Map.Transient.of();
        for(Entry<IStrategoTerm, IStrategoTerm> e : relation.entrySet()) {
            final IStrategoTerm key = e.getKey();
            final IStrategoTerm value = e.getValue();
            if(result.containsKey(key)) {
                final IStrategoTerm oldValue = result.get(key);
                env.setCurrent(f.makeTuple(oldValue, value));
                if(!merge.evaluate(env)) {
                    return false;
                }
                result.__put(key, env.current());
            } else {
                result.__put(key, value);
            }
        }

        env.setCurrent(new StrategoImmutableMap(result.freeze()));
        return true;
    }
}

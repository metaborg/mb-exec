package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.BinaryRelation;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import java.util.Map;

public class SSL_immutable_map_to_relation extends AbstractPrimitive {

    protected SSL_immutable_map_to_relation() {
        super("SSL_immutable_map_to_relation", 0, 0);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }

        final StrategoImmutableMap map = (StrategoImmutableMap) env.current();
        final BinaryRelation.Transient<IStrategoTerm, IStrategoTerm> result = BinaryRelation.Transient.of();

        for(Map.Entry<IStrategoTerm, IStrategoTerm> e : map.backingMap.entrySet()) {
            final IStrategoTerm key = e.getKey();
            final IStrategoTerm value = e.getValue();
            result.__insert(key, value);
        }

        env.setCurrent(new StrategoImmutableRelation(result.freeze()));
        return true;
    }
}

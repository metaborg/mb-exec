package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Set;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_map_keys_to_set extends AbstractPrimitive {

    protected SSL_immutable_map_keys_to_set() {
        super("SSL_immutable_map_keys_to_set", 0, 0);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }

        final StrategoImmutableMap map = (StrategoImmutableMap) env.current();
        final Set.Transient<IStrategoTerm> set = Set.Transient.of();
        set.__insertAll(map.backingMap.keySet());

        env.setCurrent(new StrategoImmutableSet(set.freeze()));
        return true;
    }
}

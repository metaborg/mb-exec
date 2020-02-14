package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Set;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_relation_keys_set extends AbstractPrimitive {

    protected SSL_immutable_relation_keys_set() {
        super("SSL_immutable_relation_keys_set", 0, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }

        final StrategoImmutableRelation relation = (StrategoImmutableRelation) env.current();
        final Set.Transient<IStrategoTerm> set = Set.Transient.of();
        set.__insertAll(relation.backingRelation.keySet());

        env.setCurrent(new StrategoImmutableSet(set.freeze()));
        return true;
    }
}

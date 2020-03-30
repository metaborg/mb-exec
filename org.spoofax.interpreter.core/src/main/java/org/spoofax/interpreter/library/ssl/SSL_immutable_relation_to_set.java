package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

import io.usethesource.capsule.Set;

public class SSL_immutable_relation_to_set extends AbstractPrimitive {

    protected SSL_immutable_relation_to_set() {
        super("SSL_immutable_relation_to_set", 0, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }

        final StrategoImmutableRelation relation = (StrategoImmutableRelation) env.current();
        final Set.Transient<IStrategoTerm> result = Set.Transient.of();
        for(IStrategoTerm pair : relation) {
            result.__insert(pair);
        }

        env.setCurrent(new StrategoImmutableSet(result.freeze()));
        return true;
    }
}

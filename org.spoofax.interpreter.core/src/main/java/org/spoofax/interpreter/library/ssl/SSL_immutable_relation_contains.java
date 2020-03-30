package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_relation_contains extends AbstractPrimitive {

    protected SSL_immutable_relation_contains() {
        super("SSL_immutable_relation_contains", 0, 2);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }

        final StrategoImmutableRelation relation = (StrategoImmutableRelation) env.current();
        final IStrategoTerm key = targs[0];
        final IStrategoTerm value = targs[1];

        return relation.backingRelation.containsEntry(key, value);
    }
}

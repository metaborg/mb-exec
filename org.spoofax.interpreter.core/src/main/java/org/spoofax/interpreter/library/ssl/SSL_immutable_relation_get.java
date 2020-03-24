package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Set;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_relation_get extends AbstractPrimitive {

    protected SSL_immutable_relation_get() {
        super("SSL_immutable_relation_get", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }

        final StrategoImmutableRelation relation = (StrategoImmutableRelation) env.current();
        final IStrategoTerm key = targs[0];

        final Set.Immutable<IStrategoTerm> newCurrent = relation.backingRelation.get(key);
        env.setCurrent(new StrategoImmutableSet(newCurrent));
        return true;
    }

}

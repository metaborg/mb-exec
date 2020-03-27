package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_relation_inverse extends AbstractPrimitive {

    protected SSL_immutable_relation_inverse() {
        super("SSL_immutable_relation_inverse", 0, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }

        final StrategoImmutableRelation relationTerm = (StrategoImmutableRelation) env.current();
        env.setCurrent(new StrategoImmutableRelation(relationTerm.backingRelation.inverse()));

        return true;
    }
}

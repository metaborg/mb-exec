package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.BinaryRelation;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_relation_put extends AbstractPrimitive {

    protected SSL_immutable_relation_put() {
        super("SSL_immutable_relation_put", 0, 2);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }

        final StrategoImmutableRelation map = (StrategoImmutableRelation) env.current();
        final IStrategoTerm key = targs[0];
        final IStrategoTerm value = targs[1];

        env.setCurrent(new StrategoImmutableRelation(
            (BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm>) map.backingRelation.__insert(key, value)));
        return true;
    }

}

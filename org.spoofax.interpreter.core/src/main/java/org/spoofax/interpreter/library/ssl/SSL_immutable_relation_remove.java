package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.BinaryRelation;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_relation_remove extends AbstractPrimitive {

    protected SSL_immutable_relation_remove() {
        super("SSL_immutable_relation_remove", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }

        final BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> relation =
            ((StrategoImmutableRelation) env.current()).backingRelation;
        final IStrategoTerm key = targs[0];
        final BinaryRelation.Transient<IStrategoTerm, IStrategoTerm> result = relation.asTransient();
        result.__remove(key);

        env.setCurrent(new StrategoImmutableRelation(result.freeze()));
        return true;
    }

}

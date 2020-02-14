package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.BinaryRelation;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_relation_subtract extends AbstractPrimitive {

    protected SSL_immutable_relation_subtract() {
        super("SSL_immutable_relation_subtract", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }
        if(!(targs[0] instanceof StrategoImmutableRelation)) {
            return false;
        }

        final BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> one =
            ((StrategoImmutableRelation) env.current()).backingRelation;
        final BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> other =
            ((StrategoImmutableRelation) targs[0]).backingRelation;

        final BinaryRelation.Transient<IStrategoTerm, IStrategoTerm> result = one.asTransient();
        for(IStrategoTerm key : other.keySet()) {
            result.__remove(key);
        }

        env.setCurrent(new StrategoImmutableRelation(result.freeze()));
        return true;
    }

}

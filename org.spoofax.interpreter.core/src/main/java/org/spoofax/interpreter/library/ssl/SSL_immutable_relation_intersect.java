package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.BinaryRelation;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import java.util.Map;

public class SSL_immutable_relation_intersect extends AbstractPrimitive {

    protected SSL_immutable_relation_intersect() {
        super("SSL_immutable_relation_intersect", 0, 1);
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

        env.setCurrent(new StrategoImmutableRelation(intersect(one, other)));
        return true;
    }

    public static BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> intersect(
        BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> one,
        BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> other) {
        final BinaryRelation.Transient<IStrategoTerm, IStrategoTerm> result = BinaryRelation.Transient.of();
        for(Map.Entry<IStrategoTerm, IStrategoTerm> e : one.entrySet()) {
            if(other.containsEntry(e.getKey(), e.getValue())) {
                result.__insert(e.getKey(), e.getValue());
            }
        }
        return result.freeze();
    }
}

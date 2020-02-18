package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.BinaryRelation;
import io.usethesource.capsule.Set;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_set_cartesian_product extends AbstractPrimitive {

    protected SSL_immutable_set_cartesian_product() {
        super("SSL_immutable_set_cartesian_product", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableSet)) {
            return false;
        }
        if(!(targs[0] instanceof StrategoImmutableSet)) {
            return false;
        }

        final StrategoImmutableSet left = (StrategoImmutableSet) env.current();
        final StrategoImmutableSet right = (StrategoImmutableSet) targs[0];

        env.setCurrent(new StrategoImmutableRelation(cartesianProduct(left.backingSet, right.backingSet)));
        return true;
    }

    public static BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> cartesianProduct(
        Set.Immutable<IStrategoTerm> left, Set.Immutable<IStrategoTerm> right) {
        final BinaryRelation.Transient<IStrategoTerm, IStrategoTerm> result = BinaryRelation.Transient.of();

        for(IStrategoTerm key : left) {
            result.__insert(key, right);
        }

        return result.freeze();
    }
}

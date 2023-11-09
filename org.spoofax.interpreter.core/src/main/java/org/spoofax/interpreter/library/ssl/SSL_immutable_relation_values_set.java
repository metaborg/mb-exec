package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Set;

import org.metaborg.util.collection.CapsuleUtil;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_relation_values_set extends AbstractPrimitive {

    protected SSL_immutable_relation_values_set() {
        super("SSL_immutable_relation_values_set", 0, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }

        final StrategoImmutableRelation map = (StrategoImmutableRelation) env.current();
        final Set.Transient<IStrategoTerm> result = CapsuleUtil.transientSet();
        result.__insertAll(map.backingRelation.inverse().keySet());

        env.setCurrent(new StrategoImmutableSet(result.freeze()));
        return true;
    }
}

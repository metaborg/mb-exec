package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_relation_keys extends AbstractPrimitive {

    protected SSL_immutable_relation_keys() {
        super("SSL_immutable_relation_keys", 0, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }

        final StrategoImmutableRelation relation = (StrategoImmutableRelation) env.current();
        env.setCurrent(env.getFactory().makeList(relation.backingRelation.keySet()));
        return true;
    }
}

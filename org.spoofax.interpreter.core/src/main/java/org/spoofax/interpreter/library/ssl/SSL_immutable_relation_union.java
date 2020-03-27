package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

import static org.spoofax.interpreter.library.ssl.StrategoImmutableRelation.union;

public class SSL_immutable_relation_union extends AbstractPrimitive {

    protected SSL_immutable_relation_union() {
        super("SSL_immutable_relation_union", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }
        if(!(targs[0] instanceof StrategoImmutableRelation)) {
            return false;
        }

        final StrategoImmutableRelation one = (StrategoImmutableRelation) env.current();
        final StrategoImmutableRelation other = (StrategoImmutableRelation) targs[0];

        env.setCurrent(union(one, other));
        return true;
    }

}

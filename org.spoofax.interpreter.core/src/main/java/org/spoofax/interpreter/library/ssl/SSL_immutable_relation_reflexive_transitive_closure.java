package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

import static org.spoofax.interpreter.library.ssl.StrategoImmutableRelation.reflTransClos;
import static org.spoofax.interpreter.library.ssl.StrategoImmutableRelation.transitiveClosure;
import static org.spoofax.interpreter.library.ssl.StrategoImmutableRelation.union;

public class SSL_immutable_relation_reflexive_transitive_closure extends AbstractPrimitive {

    protected SSL_immutable_relation_reflexive_transitive_closure() {
        super("SSL_immutable_relation_reflexive_transitive_closure", 0, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }

        final StrategoImmutableRelation map = (StrategoImmutableRelation) env.current();

        env.setCurrent(new StrategoImmutableRelation(reflTransClos(map.backingRelation).freeze()));
        return true;
    }

}

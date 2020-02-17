package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.BinaryRelation;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import java.util.Map.Entry;

import static org.spoofax.interpreter.library.ssl.SSL_immutable_relation_transitive_closure.transitiveClosure;
import static org.spoofax.interpreter.library.ssl.SSL_immutable_relation_union.union;

public class SSL_immutable_relation_reflexive_transitive_closure extends AbstractPrimitive {

    protected SSL_immutable_relation_reflexive_transitive_closure() {
        super("SSL_immutable_relation_reflexive_transitive_closure", 0, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }

        final BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> map =
            ((StrategoImmutableRelation) env.current()).backingRelation;

        env.setCurrent(new StrategoImmutableRelation(union(transitiveClosure(map), reflexiveClosure(map))));
        return true;
    }

    public static BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> reflexiveClosure(
        BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> map) {
        final BinaryRelation.Transient<IStrategoTerm, IStrategoTerm> result = map.asTransient();

        for(Entry<IStrategoTerm, IStrategoTerm> e : map.entrySet()) {
            final IStrategoTerm key = e.getKey();
            final IStrategoTerm value = e.getValue();
            result.__insert(key, key);
            result.__insert(value, value);
        }
        return result.freeze();
    }
}

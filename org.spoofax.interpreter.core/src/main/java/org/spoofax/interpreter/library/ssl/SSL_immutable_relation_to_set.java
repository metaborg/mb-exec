package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.BinaryRelation;
import io.usethesource.capsule.Set;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.util.EntryAsPairIterator;
import java.util.Iterator;

public class SSL_immutable_relation_to_set extends AbstractPrimitive {

    protected SSL_immutable_relation_to_set() {
        super("SSL_immutable_relation_to_set", 0, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }

        final BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> relation =
            ((StrategoImmutableRelation) env.current()).backingRelation;
        final Set.Transient<IStrategoTerm> result = Set.Transient.of();
        for(Iterator<IStrategoTerm> iterator = new EntryAsPairIterator(relation.entryIterator()); iterator.hasNext(); ) {
            result.__insert(iterator.next());
        }

        env.setCurrent(new StrategoImmutableSet(result.freeze()));
        return true;
    }
}

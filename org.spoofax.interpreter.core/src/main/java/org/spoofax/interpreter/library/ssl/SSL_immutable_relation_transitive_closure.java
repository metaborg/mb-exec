package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.BinaryRelation;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class SSL_immutable_relation_transitive_closure extends AbstractPrimitive {

    protected SSL_immutable_relation_transitive_closure() {
        super("SSL_immutable_relation_transitive_closure", 0, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }

        final BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> map = ((StrategoImmutableRelation) env.current()).backingRelation;

        env.setCurrent(new StrategoImmutableRelation(transitiveClosure(map)));
        return true;
    }

    public static BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> transitiveClosure(
        BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> map) {
        HashSet<Entry<IStrategoTerm, IStrategoTerm>> frontier1 = new HashSet<>(map.entrySet());
        HashSet<Entry<IStrategoTerm, IStrategoTerm>> frontier2 = new HashSet<>();
        final BinaryRelation.Transient<IStrategoTerm, IStrategoTerm> result = map.asTransient();
        boolean done = false;
        while(!done) {
            done = true;
            for(Entry<IStrategoTerm, IStrategoTerm> e : frontier1) {
                for(IStrategoTerm value : map.get(e.getValue())) {
                    if(!result.containsEntry(e.getKey(), value)) {
                        frontier2.add(new AbstractMap.SimpleImmutableEntry<>(e.getKey(), value));
                        result.__insert(e.getKey(), value);
                        done = false;
                    }
                }
            }
            HashSet<Entry<IStrategoTerm, IStrategoTerm>> tmp = frontier1; // swap & clear for better memory perf
            frontier1 = frontier2;
            frontier2 = tmp;
            frontier2.clear();
        }
        return result.freeze();
    }
}

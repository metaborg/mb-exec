package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;
import io.usethesource.capsule.Set;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import java.util.Iterator;

public class SSL_immutable_map_intersect_set extends AbstractPrimitive {

    protected SSL_immutable_map_intersect_set() {
        super("SSL_immutable_map_intersect_set", 1, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs)
            throws InterpreterException {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }
        if(!(targs[0] instanceof StrategoImmutableSet)) {
            return false;
        }

        final Map.Transient<IStrategoTerm, IStrategoTerm> one = ((StrategoImmutableMap) env.current()).backingMap.asTransient();
        final Set.Immutable<IStrategoTerm> other = ((StrategoImmutableSet) targs[0]).backingSet;
        for(Iterator<IStrategoTerm> iterator = one.keyIterator(); iterator.hasNext(); ) {
            IStrategoTerm key = iterator.next();
            if(!other.contains(key)) {
                iterator.remove();
            }
        }

        env.setCurrent(new StrategoImmutableMap(one.freeze()));
        return true;
    }
}

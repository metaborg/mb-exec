package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class SSL_immutable_map_to_list extends AbstractPrimitive {

    protected SSL_immutable_map_to_list() {
        super("SSL_immutable_map_to_list", 0, 0);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableMap)) {
            return false;
        }
        final ITermFactory factory = env.getFactory();

        final Map.Immutable<IStrategoTerm, IStrategoTerm> map = ((StrategoImmutableMap) env.current()).backingMap;
        final IStrategoTerm[] array = new IStrategoTerm[map.size()];
        int i = 0;
        for(java.util.Map.Entry<IStrategoTerm, IStrategoTerm> e : map.entrySet()) {
            array[i] = factory.makeTuple(e.getKey(), e.getValue());
            i += 1;
        }

        env.setCurrent(factory.makeList(array));
        return true;
    }
}

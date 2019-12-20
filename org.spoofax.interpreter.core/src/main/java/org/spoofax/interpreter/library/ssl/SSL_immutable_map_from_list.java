package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class SSL_immutable_map_from_list extends AbstractPrimitive {

    protected SSL_immutable_map_from_list() {
        super("SSL_immutable_map_from_list", 0, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs)
            throws InterpreterException {
        if(!Tools.isTermList(env.current())) {
            return false;
        }
        final ITermFactory factory = env.getFactory();

        final IStrategoList list = (IStrategoList) env.current();
        final Map.Transient<IStrategoTerm, IStrategoTerm> map = Map.Transient.of();
        for(IStrategoTerm t : list) {
            if(!(Tools.isTermTuple(t) && t.getSubtermCount() == 2)) {
                return false;
            }
            final IStrategoTerm key = t.getSubterm(0);
            if(!map.containsKey(key)) {
                map.__put(key, t.getSubterm(1));
            }
        }

        env.setCurrent(new StrategoImmutableMap(map.freeze()));
        return true;
    }
}

package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;

import org.metaborg.util.collection.CapsuleUtil;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.util.TermUtils;

public class SSL_immutable_map_from_list extends AbstractPrimitive {

    protected SSL_immutable_map_from_list() {
        super("SSL_immutable_map_from_list", 1, 0);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        if(!TermUtils.isList(env.current())) {
            return false;
        }
        final Strategy merge = sargs[0];
        final ITermFactory f = env.getFactory();

        final IStrategoList list = (IStrategoList) env.current();
        final Map.Transient<IStrategoTerm, IStrategoTerm> map = CapsuleUtil.transientMap();
        for(IStrategoTerm t : list) {
            if(!TermUtils.isTuple(t, 2)) {
                return false;
            }
            final IStrategoTerm key = t.getSubterm(0);
            final IStrategoTerm value = t.getSubterm(1);
            if(map.containsKey(key)) {
                final IStrategoTerm oldValue = map.get(key);
                env.setCurrent(f.makeTuple(oldValue, value));
                if(!merge.evaluate(env)) {
                    return false;
                }
                map.__put(key, env.current());
            } else {
                map.__put(key, value);
            }
        }

        env.setCurrent(new StrategoImmutableMap(map.freeze()));
        return true;
    }
}

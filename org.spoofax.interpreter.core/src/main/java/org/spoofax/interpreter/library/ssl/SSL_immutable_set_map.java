package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Set;

import org.metaborg.util.collection.CapsuleUtil;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_set_map extends AbstractPrimitive {

    protected SSL_immutable_set_map() {
        super("SSL_immutable_set_map", 2, 0);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        if(!(env.current() instanceof StrategoImmutableSet)) {
            return false;
        }

        final Set.Immutable<IStrategoTerm> set = ((StrategoImmutableSet) env.current()).backingSet;
        final Set.Transient<IStrategoTerm> resultSet = CapsuleUtil.transientSet();
        for(IStrategoTerm value : set) {
            env.setCurrent(value);
            if(!sargs[0].evaluate(env)) {
                return false;
            }
            if(resultSet.contains(env.current())) {
                if(!sargs[1].evaluate(env)) {
                    continue;
                }
            }
            resultSet.__insert(env.current());
        }

        env.setCurrent(new StrategoImmutableSet(resultSet.freeze()));
        return true;
    }
}

package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_set_union extends AbstractPrimitive {

    protected SSL_immutable_set_union() {
        super("SSL_immutable_set_union", 0, 1);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        if(!(env.current() instanceof StrategoImmutableSet)) {
            return false;
        }
        if(!(targs[0] instanceof StrategoImmutableSet)) {
            return false;
        }

        final StrategoImmutableSet one = (StrategoImmutableSet) env.current();
        final StrategoImmutableSet other = (StrategoImmutableSet) targs[0];

        env.setCurrent(new StrategoImmutableSet(one.backingSet.union(other.backingSet)));
        return true;
    }
}

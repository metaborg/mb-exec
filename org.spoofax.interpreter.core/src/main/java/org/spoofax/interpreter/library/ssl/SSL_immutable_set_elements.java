package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_set_elements extends AbstractPrimitive {

    protected SSL_immutable_set_elements() {
        super("SSL_immutable_set_elements", 0, 0);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        if(!(env.current() instanceof StrategoImmutableSet)) {
            return false;
        }

        final StrategoImmutableSet set = (StrategoImmutableSet) env.current();

        env.setCurrent(env.getFactory().makeList(set.backingSet));
        return true;
    }
}

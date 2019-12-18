package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;
import io.usethesource.capsule.Set;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class SSL_immutable_set_from_list extends AbstractPrimitive {

    protected SSL_immutable_set_from_list() {
        super("SSL_immutable_set_from_list", 0, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs)
            throws InterpreterException {
        if(!Tools.isTermList(env.current())) {
            return false;
        }

        final IStrategoList list = (IStrategoList) env.current();
        final Set.Transient<IStrategoTerm> map = Set.Transient.of();
        for(IStrategoTerm t : list) {
            map.__insert(t);
        }

        env.setCurrent(new StrategoImmutableSet(map.freeze()));
        return true;
    }
}

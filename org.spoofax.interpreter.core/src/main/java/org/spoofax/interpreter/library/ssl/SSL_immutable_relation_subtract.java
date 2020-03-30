package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

import static org.spoofax.interpreter.library.ssl.StrategoImmutableRelation.subtract;

public class SSL_immutable_relation_subtract extends AbstractPrimitive {

    protected SSL_immutable_relation_subtract() {
        super("SSL_immutable_relation_subtract", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }
        if(!(targs[0] instanceof StrategoImmutableRelation)) {
            return false;
        }

        final StrategoImmutableRelation left = (StrategoImmutableRelation) env.current();
        final StrategoImmutableRelation right = (StrategoImmutableRelation) targs[0];

        env.setCurrent(subtract(left, right));
        return true;
    }

}

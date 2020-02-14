package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.BinaryRelation;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_immutable_relation_union extends AbstractPrimitive {

    protected SSL_immutable_relation_union() {
        super("SSL_immutable_relation_union", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }
        if(!(targs[0] instanceof StrategoImmutableRelation)) {
            return false;
        }

        final BinaryRelation.Transient<IStrategoTerm, IStrategoTerm> one =
            ((StrategoImmutableRelation) env.current()).backingRelation.asTransient();
        final BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> other =
            ((StrategoImmutableRelation) targs[0]).backingRelation;
        for(java.util.Map.Entry<IStrategoTerm, IStrategoTerm> e : other.entrySet()) {
            one.__put(e.getKey(), e.getValue());
        }

        env.setCurrent(new StrategoImmutableRelation(one.freeze()));
        return true;
    }

}

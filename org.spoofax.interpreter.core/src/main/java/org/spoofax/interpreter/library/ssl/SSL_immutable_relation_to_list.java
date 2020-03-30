package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

import io.usethesource.capsule.BinaryRelation;

public class SSL_immutable_relation_to_list extends AbstractPrimitive {

    protected SSL_immutable_relation_to_list() {
        super("SSL_immutable_relation_to_list", 0, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }
        final ITermFactory factory = env.getFactory();

        final BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> relation =
            ((StrategoImmutableRelation) env.current()).backingRelation;
        final IStrategoTerm[] array =
            relation.tupleStream((k, v) -> factory.makeTuple(k, v)).toArray(IStrategoTerm[]::new);

        env.setCurrent(factory.makeList(array));
        return true;
    }
}

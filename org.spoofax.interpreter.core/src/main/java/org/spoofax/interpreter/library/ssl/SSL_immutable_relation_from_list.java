package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.BinaryRelation;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.util.TermUtils;

public class SSL_immutable_relation_from_list extends AbstractPrimitive {

    protected SSL_immutable_relation_from_list() {
        super("SSL_immutable_relation_from_list", 0, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!TermUtils.isList(env.current())) {
            return false;
        }

        final IStrategoList list = (IStrategoList) env.current();
        final BinaryRelation.Transient<IStrategoTerm, IStrategoTerm> relation = BinaryRelation.Transient.of();
        for(IStrategoTerm t : list) {
            if(!(TermUtils.isTuple(t) && t.getSubtermCount() == 2)) {
                return false;
            }
            relation.__insert(t.getSubterm(0), t.getSubterm(1));
        }

        env.setCurrent(new StrategoImmutableRelation(relation.freeze()));
        return true;
    }
}

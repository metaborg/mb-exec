package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Set;

import org.metaborg.util.collection.CapsuleUtil;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.util.TermUtils;

public class SSL_immutable_set_from_list extends AbstractPrimitive {

    protected SSL_immutable_set_from_list() {
        super("SSL_immutable_set_from_list", 0, 0);
    }

    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        if(!TermUtils.isList(env.current())) {
            return false;
        }

        final IStrategoList list = (IStrategoList) env.current();
        final Set.Transient<IStrategoTerm> map = CapsuleUtil.transientSet();
        for(IStrategoTerm t : list) {
            if(!map.contains(t)) {
                map.__insert(t);
            }
        }

        env.setCurrent(new StrategoImmutableSet(map.freeze()));
        return true;
    }
}

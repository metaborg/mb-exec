package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.BinaryRelation;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class SSL_immutable_relation_filter extends AbstractPrimitive {

    protected SSL_immutable_relation_filter() {
        super("SSL_immutable_relation_filter", 1, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        if(!(env.current() instanceof StrategoImmutableRelation)) {
            return false;
        }
        final Strategy mapping = sargs[0];
        final ITermFactory f = env.getFactory();

        final BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> relation =
            ((StrategoImmutableRelation) env.current()).backingRelation;
        final BinaryRelation.Transient<IStrategoTerm, IStrategoTerm> resultRelation = BinaryRelation.Transient.of();
        for(java.util.Map.Entry<IStrategoTerm, IStrategoTerm> e : relation.entrySet()) {
            env.setCurrent(f.makeTuple(e.getKey(), e.getValue()));
            if(!mapping.evaluate(env)) {
                continue;
            }
            final IStrategoTerm current = env.current();
            if(!(Tools.isTermTuple(current)) && current.getSubtermCount() == 2) {
                return false;
            }
            final IStrategoTerm newKey = current.getSubterm(0);
            final IStrategoTerm newValue = current.getSubterm(1);
            resultRelation.__put(newKey, newValue);
        }

        env.setCurrent(new StrategoImmutableRelation(resultRelation.freeze()));
        return true;
    }
}

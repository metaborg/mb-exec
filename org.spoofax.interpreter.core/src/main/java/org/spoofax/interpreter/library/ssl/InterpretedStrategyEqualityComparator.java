package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.util.EqualityComparator;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTermBuilder;

class InterpretedStrategyEqualityComparator implements EqualityComparator<Object> {
    private final IContext env;
    private final Strategy comp;
    private final IStrategoTermBuilder f;

    InterpretedStrategyEqualityComparator(IContext env, Strategy comp) {
        this.env = env;
        this.comp = comp;
        this.f = env.getFactory();
    }

    @Override public boolean equals(Object o1, Object o2) {
        if(o1 == null || o2 == null || !(o1 instanceof IStrategoTerm) || !(o2 instanceof IStrategoTerm)) {
            return false;
        }
        IStrategoTerm left = (IStrategoTerm) o1;
        IStrategoTerm right = (IStrategoTerm) o2;

        IStrategoTerm original = env.current();
        env.setCurrent(f.makeTuple(left, right));
        try {
            return comp.evaluate(env);
        } catch(InterpreterException e) {
            throw new RuntimeException(e);
        } finally {
            env.setCurrent(original);
        }
    }
}

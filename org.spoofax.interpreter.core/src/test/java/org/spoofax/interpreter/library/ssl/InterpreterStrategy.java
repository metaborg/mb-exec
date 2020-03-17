package org.spoofax.interpreter.library.ssl;

import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.stratego.Hook;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.stratego.StupidFormatter;
import org.spoofax.interpreter.terms.IStrategoTerm;

interface InterpreterStrategy {
    static Strategy of(Function<IStrategoTerm, IStrategoTerm> f) {
        return new Strategy() {
            @Override public IConstruct eval(IContext e) throws InterpreterException {
                final @Nullable IStrategoTerm result = f.apply(e.current());
                final Hook hook = getHook().pop();
                if(result != null) {
                    e.setCurrent(result);
                    return hook.onSuccess(e);
                } else {
                    return hook.onFailure(e);
                }
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
                // ignore
            }
        };
    }

    static Strategy test(Predicate<IStrategoTerm> f) {
        return new Strategy() {
            @Override public IConstruct eval(IContext e) throws InterpreterException {
                final Hook hook = getHook().pop();
                if(f.test(e.current())) {
                    return hook.onSuccess(e);
                } else {
                    return hook.onFailure(e);
                }
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
                // ignore
            }
        };
    }

    static Strategy constant(IStrategoTerm c) {
        return new Strategy() {
            @Override public IConstruct eval(IContext e) throws InterpreterException {
                e.setCurrent(c);
                return getHook().pop().onSuccess(e);
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
                // ignore
            }
        };
    }

    Strategy fail = new Strategy() {
        @Override
        public IConstruct eval(IContext e) throws InterpreterException {
            return getHook().pop().onFailure(e);
        }

        @Override
        public void prettyPrint(StupidFormatter fmt) {
            // ignore
        }
    };

    Strategy id = new Strategy() {
        @Override
        public IConstruct eval(IContext e) throws InterpreterException {
            return getHook().pop().onSuccess(e);
        }

        @Override
        public void prettyPrint(StupidFormatter fmt) {
            // ignore
        }
    };
}

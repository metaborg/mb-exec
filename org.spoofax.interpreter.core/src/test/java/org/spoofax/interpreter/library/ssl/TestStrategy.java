package org.spoofax.interpreter.library.ssl;

import org.metaborg.util.functions.CheckedFunction2;
import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.stratego.Hook;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.stratego.StupidFormatter;
import java.util.Stack;

interface TestStrategy {
     static Strategy of(CheckedFunction2<IContext, Stack<Hook>, IConstruct, InterpreterException> f) {
        return new Strategy() {
            @Override public IConstruct eval(IContext e) throws InterpreterException {
                return f.apply(e, getHook());
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
                // ignore
            }
        };
    }
}

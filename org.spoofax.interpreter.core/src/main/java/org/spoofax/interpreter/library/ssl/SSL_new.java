package org.spoofax.interpreter.library.ssl;

import java.util.WeakHashMap;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class SSL_new extends AbstractPrimitive {
    private static class CounterData {
        public int alphaCounter;
        public int counter;
    }


    private static final int letterA = 'a';
    private static final WeakHashMap<IContext, CounterData> countersPerContext = new WeakHashMap<>();


    protected SSL_new() {
        super("SSL_new", 0, 0);
    }


    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        final ITermFactory factory = env.getFactory();

        synchronized(countersPerContext) {
            CounterData counter;
            counter = countersPerContext.get(env);
            if(counter == null) {
                counter = new CounterData();
                countersPerContext.put(env, counter);
            }

            String s;
            IStrategoString result;
            do {
                counter.alphaCounter++;
                if(counter.alphaCounter > 25) {
                    counter.alphaCounter = 0;
                    counter.counter++;
                    if(counter.counter < 0) {
                        counter.counter = 0;
                    }
                }
                s = (char) (letterA + counter.alphaCounter) + "_" + counter.counter;
            } while((result = factory.tryMakeUniqueString(s)) == null);

            env.setCurrent(result);
            return true;
        }
    }
}

package org.spoofax.interpreter.library.ssl;

import static org.spoofax.interpreter.core.Tools.isTermAppl;

import java.util.HashSet;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.TermFactory;

public class SSL_newname extends AbstractPrimitive {
	
    // static weak set, like TermFactory.asyncStringPool
    // weak to avoid accumulating entries with long-lived/interactive sessions
    private static final WeakHashMap<String, AtomicInteger> asyncCounters =
        new WeakHashMap<String, AtomicInteger>();
	
    // non-static strong-reference set
    // to keep counters alive as long as this Stratego instance exists
    private final HashSet<String> asyncMyCounters = new HashSet<String>();
    
	SSL_newname () {
		super("SSL_newname", 0, 1);
	}
	
	@Override
	public boolean call(IContext env, Strategy[] svars,
			IStrategoTerm[] tvars) throws InterpreterException {

        final String prefix;

	    if (Tools.isTermString(tvars[0])) {
	        prefix = ((IStrategoString) tvars[0]).stringValue();
	    } else if (isTermAppl(tvars[0])) {
	        SSLLibrary library = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
	        library.get("SSL_get_constructor").call(env, svars, tvars);
	        prefix = ((IStrategoString) env.current()).stringValue();
	    } else {
	        SSLLibrary library = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
            return library.get("SSL_new").call(env, svars, tvars);
	    }

        ITermFactory factory = env.getFactory();
	    AtomicInteger counter;
	    
	    synchronized (asyncCounters) {
	        counter = asyncCounters.get(prefix);
	        asyncMyCounters.add(prefix);
	        if (counter == null) {
	            counter = new AtomicInteger();
	            asyncCounters.put(prefix, counter);
	        }
	    }
	    
        String result;
        IStrategoTerm resultTerm;
        synchronized (TermFactory.class) { // additional, non-essential lock
            do {
                int counterValue = getNextValue(prefix, counter);
                result = prefix + counterValue;
            } while ((resultTerm = factory.tryMakeUniqueString(result)) == null);
        }

        env.setCurrent(resultTerm);
        
		return true;
	}

    private int getNextValue(String prefix, AtomicInteger counter) {
        int result;
        for (;;) {
            result = counter.getAndIncrement();
            if (result >= 0) {
                break;
            } else if (counter.compareAndSet(result, 0)) {
                return 0;
            }
        }
        return result;
    }

}

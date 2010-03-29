package org.spoofax.interpreter.library.ssl;

import static org.spoofax.interpreter.core.Tools.isTermAppl;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class SSL_newname extends AbstractPrimitive {
	
    // static, like TermFactory.asyncStringPool
    private static HashMap<String, AtomicInteger> asyncCounters =
        new HashMap<String, AtomicInteger>();
	
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
	        if (counter == null) {
	            counter = new AtomicInteger();
	            asyncCounters.put(prefix, counter);
	        }
	    }
	    
        String result;
        do {
        	result = prefix + counter.getAndIncrement();
        } while (factory.hasConstructor(result, 0));

        env.setCurrent(factory.makeString(result));
        
		return true;
	}

}

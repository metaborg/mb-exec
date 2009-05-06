package org.spoofax.interpreter.library.ssl;

import java.util.WeakHashMap;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class SSL_newname extends AbstractPrimitive {
	
    private WeakHashMap<String, Integer> counters = new WeakHashMap<String, Integer>();
	
	SSL_newname () {
		super("SSL_newname", 0, 1);
	}
	
	@Override
	public boolean call(IContext env, Strategy[] svars,
			IStrategoTerm[] tvars) throws InterpreterException {

        final String prefix;

	    if (Tools.isTermString(tvars[0])) {
	        prefix = ((IStrategoString) tvars[0]).stringValue();
	    } else {
	        SSLLibrary library = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
	        library.get("SSL_get_constructor").call(env, svars, tvars);
	        prefix = ((IStrategoString) env.current()).stringValue();
	    }
        
        ITermFactory factory = env.getFactory();
	    Integer oldCounter = counters.get(prefix);
	    int counter = oldCounter == null ? 0 : oldCounter;
	    
        String result;
        do {
        	result = prefix + counter++;
        } while (factory.hasConstructor(result, 0));

        counters.put(prefix, counter);
        env.setCurrent(factory.makeString(result));
        
		return true;
	}

}

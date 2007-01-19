package org.spoofax.interpreter.library.ssl;

import java.util.HashSet;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_newname extends AbstractPrimitive {
	private HashSet<String> set;
	
	SSL_newname () {
		super("SSL_newname", 0, 1);
		set = new HashSet<String>();
	}
	
	@Override
	public boolean call(IContext env, IConstruct[] svars,
			IStrategoTerm[] tvars) throws InterpreterException {
        if(!Tools.isTermString(tvars[0]))
            return false;
        
        String var = tvars[0].toString();
        
        int n = 0;
        
        String s;
        do {
        	s = var + n++;
        } while (set.contains(s));

        env.setCurrent(env.getFactory().makeString(s));
        
		return true;
	}

}

package org.spoofax.interpreter.library.java;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class JFF_get_constructor extends AbstractPrimitive {

	protected JFF_get_constructor() {
		super("JFF_get_constructor", 0, 2);
	}

	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
			throws InterpreterException {
		
		if(!Tools.isTermAppl(tvars[0]))
			return false;
		if(!Tools.isTermList(tvars[1]))
			return false;
		
		JFFLibrary lib = JFFLibrary.instance(env);
		
		Object o = lib.unwrapObject((IStrategoAppl)tvars[0]);
		
		if(o == null || !(o instanceof Class))
			return false;
		
		Class<?> c = (Class<?>)o;
		
		IStrategoTerm[] ls = ((IStrategoList)tvars[1]).getAllSubterms(); 
		Class<?>[] args = new Class<?>[ls.length];
		for(int i = 0; i < ls.length; i++) {
			 Object x = lib.unpackTerm(ls[i]);
			 if(x instanceof Class)
				 args[i] = (Class<?>) x;
			 else
				 return false;
		}
		
		try {
			Object x = c.getConstructor(args);
			env.setCurrent(lib.wrapObject(x));
		} catch (SecurityException e) {
			e.printStackTrace();
			return false;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}

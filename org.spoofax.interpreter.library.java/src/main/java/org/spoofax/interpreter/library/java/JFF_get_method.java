package org.spoofax.interpreter.library.java;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class JFF_get_method extends AbstractPrimitive {

	protected JFF_get_method() {
		super("JFF_get_method", 0, 3);
	}

	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
			throws InterpreterException {
		
		if(!Tools.isTermString(tvars[0]))
			return false;
		if(!Tools.isTermAppl(tvars[0]))
			return false;
		if(!Tools.isTermList(tvars[1]))
			return false;
		
		JFFLibrary lib = JFFLibrary.instance(env);
		
		Object o = lib.unwrapObject((IStrategoAppl)tvars[1]);
		if(o == null || !(o instanceof Class))
			return false;
		
		Class<?> clazz = (Class<?>)o;
		
		IStrategoTerm[] ls = ((IStrategoList)tvars[2]).getAllSubterms();
		Class<?>[] argtypes = new Class<?>[ls.length];
		for(int i = 0; i < ls.length; i++) {
			Object x = lib.unpackTerm(ls[i]);
			if(!(x instanceof Class))
				return false;
			argtypes[i] = (Class<?>)x;
		}

		try {
			Object x = clazz.getMethod(Tools.asJavaString(tvars[0]), argtypes);
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

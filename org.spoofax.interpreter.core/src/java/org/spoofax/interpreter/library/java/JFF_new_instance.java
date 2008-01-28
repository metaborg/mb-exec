package org.spoofax.interpreter.library.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class JFF_new_instance extends AbstractPrimitive {

	JFF_new_instance() {
			super("JFF_new_instance", 0, 2);
	}
	
	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
			throws InterpreterException {
		
		if(!Tools.isTermAppl(tvars[0]))
			return false;
		if(!Tools.isTermList(tvars[1]))
			return false;

		JFFLibrary lib = JFFLibrary.instance(env);
		
		Object callable = lib.unwrapObject((IStrategoAppl)tvars[0]);
		Object obj = lib.unwrapObject((IStrategoAppl)tvars[0]);
		
		if(callable == null || obj == null)
			return false;
		
		if(!(callable instanceof Constructor))
			return false;
		
		IStrategoList ls = (IStrategoList)tvars[1];
		Object[] args = new Object[ls.size()];
		int idx = 0;
		for(IStrategoTerm t : ls.getAllSubterms()) {
			args[idx++] = lib.unpackTerm(t);
		}

		Constructor<?> ctor = (Constructor<?>) callable;
		
		Object o;
		try {
			o = ctor.newInstance(args);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		}

		env.setCurrent(lib.wrapObject(o));
		return true;
	}

}

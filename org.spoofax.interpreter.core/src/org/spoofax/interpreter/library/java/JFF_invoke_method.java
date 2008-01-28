package org.spoofax.interpreter.library.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class JFF_invoke_method extends AbstractPrimitive {

	JFF_invoke_method() {
		super("JFF_invoke_method", 0, 3);
	}

	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
	throws InterpreterException {
		
		if(!Tools.isTermAppl(tvars[0]))
			return false;
		if(!Tools.isTermAppl(tvars[1]))
			return false;
		if(!Tools.isTermList(tvars[2]))
			return false;

		JFFLibrary lib = JFFLibrary.instance(env);
		
		Object callable = lib.unwrapObject((IStrategoAppl)tvars[0]);
		Object obj = lib.unwrapObject((IStrategoAppl)tvars[0]);
		
		if(callable == null || obj == null)
			return false;
		
		if(!(callable instanceof Method))
			return false;
		
		IStrategoList ls = (IStrategoList)tvars[2];
		Object[] args = new Object[ls.size()];
		int idx = 0;
		for(IStrategoTerm t : ls.getAllSubterms()) {
			args[idx++] = lib.unpackTerm(t);
		}

		Method meth = (Method) callable;
		
		Object o;
		try {
			o = meth.invoke(obj, args);
		} catch (IllegalArgumentException e) {
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

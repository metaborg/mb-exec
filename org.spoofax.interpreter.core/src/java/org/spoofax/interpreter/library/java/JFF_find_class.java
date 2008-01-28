package org.spoofax.interpreter.library.java;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class JFF_find_class extends AbstractPrimitive {

	JFF_find_class() {
		super("JFF_find_class", 0, 1);
	}

	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
			throws InterpreterException {
		
		if (!Tools.isTermString(tvars[0]))
            return false;
		
		
		try {
			Class<?> c = Class.forName(Tools.asJavaString(tvars[0]));
			JFFLibrary lib = JFFLibrary.instance(env);
			env.setCurrent(lib.wrapObject(c));
		} catch (ClassNotFoundException e) {
			return false;
		}
		
		return true;
	}

}

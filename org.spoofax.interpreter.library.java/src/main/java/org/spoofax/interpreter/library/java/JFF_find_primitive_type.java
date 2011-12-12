package org.spoofax.interpreter.library.java;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class JFF_find_primitive_type extends AbstractPrimitive {

	protected JFF_find_primitive_type() {
		super("JFF_find_primitive_type", 0, 1);
	}

	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
			throws InterpreterException {
		if(!Tools.isTermString(tvars[0]))
			return false;
		
		JFFLibrary lib = JFFLibrary.instance(env);
		
		final String typeName = Tools.asJavaString(tvars[0]);
		
		if("int".equals(typeName)) {
			env.setCurrent(lib.wrapObject(Integer.TYPE));
		} else if("float".equals(typeName)) {
			env.setCurrent(lib.wrapObject(Float.TYPE));
		} else if("double".equals(typeName)) {
			env.setCurrent(lib.wrapObject(Double.TYPE));
		} else if("char".equals(typeName)) {
			env.setCurrent(lib.wrapObject(Character.TYPE));
		} else if("byte".equals(typeName)) {
			env.setCurrent(lib.wrapObject(Byte.TYPE));
		} else if("long".equals(typeName)) {
			env.setCurrent(lib.wrapObject(Long.TYPE));
		} else if("short".equals(typeName)) {
			env.setCurrent(lib.wrapObject(Short.TYPE));
		} else {
			return false;
		}
			
		return true;
	}

}

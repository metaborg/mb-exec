package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_rmdir extends AbstractPrimitive {

	public SSL_rmdir() {
		super("SSL_rmdir", 0, 2);
	}

	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) throws InterpreterException {
		if(!Tools.isTermString(tvars[0]))
			return false;

		SSLLibrary op = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
		int result = op.getIOAgent().rmdir(Tools.asJavaString(tvars[0])) ? 0 : -1;
		env.setCurrent(env.getFactory().makeInt(result));

		return true;
	}

}

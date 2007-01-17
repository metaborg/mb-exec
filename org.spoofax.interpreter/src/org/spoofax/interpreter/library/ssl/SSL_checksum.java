package org.spoofax.interpreter.library.ssl;

import java.util.List;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_checksum extends AbstractPrimitive {

	protected SSL_checksum() {
		super("SSL_checksum", 0, 1);
	}

    @Override
    public boolean call(IContext env, List<IConstruct> svars, IStrategoTerm[] tvars) throws InterpreterException {
        env.setCurrent(env.getFactory().makeString(Integer.toString(tvars[0].hashCode())));
        return true;
    }
}

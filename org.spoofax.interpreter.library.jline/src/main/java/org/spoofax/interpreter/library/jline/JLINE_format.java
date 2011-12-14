/*
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.jline;

import jline.ANSIBuffer;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class JLINE_format extends AbstractPrimitive {

	public JLINE_format() {
		super("JLINE_format", 0, 0);
	}

	@Override
	public boolean call(final IContext env, Strategy[] svars,
			IStrategoTerm[] tvars) throws InterpreterException {
		ANSIBuffer buf = makeANSIBuffer(env);
		if(buf == null)
			return false;
		env.setCurrent(env.getFactory().makeString(buf.toString()));
		return true;
	}

	static ANSIBuffer makeANSIBuffer(final IContext env) {
		if(env.current().getTermType() != IStrategoTerm.LIST)
			return null;
		ANSIBuffer buf = new ANSIBuffer();
		for(IStrategoTerm t : env.current().getAllSubterms()) {
			if(t.getTermType() == IStrategoTerm.STRING) {
				buf.append(((IStrategoString) t).stringValue());
			} else if(t.getTermType() == IStrategoTerm.APPL) {
				String ctor = ((IStrategoAppl) t).getName();
				if(!"ANSIFragment".equals(ctor))
					return null;
				if(t.getSubterm(0).getTermType() != IStrategoTerm.INT)
					return null;
				if(t.getSubterm(1).getTermType() != IStrategoTerm.STRING)
					return null;
				int code = ((IStrategoInt) t.getSubterm(0)).intValue();
				String str = ((IStrategoString) t.getSubterm(1)).stringValue();
				buf.attrib(str, code);
			}
		}
		return buf;
	}

}

package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.interpreter.terms.BasicStrategoAppl;
import org.spoofax.interpreter.terms.BasicStrategoConstructor;
import org.spoofax.interpreter.terms.BasicStrategoString;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class DottedName extends BasicStrategoAppl {
	private final static IStrategoConstructor CTOR = new BasicStrategoConstructor("DottedName", 1);
	
	protected DottedName(String name) {
		super(CTOR, new IStrategoTerm[] { new BasicStrategoString(name) });
	}

}

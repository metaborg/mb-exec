package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.interpreter.terms.BasicStrategoAppl;
import org.spoofax.interpreter.terms.BasicStrategoConstructor;
import org.spoofax.interpreter.terms.BasicStrategoString;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class AmbName extends BasicStrategoAppl {

	private static IStrategoConstructor CTOR = new BasicStrategoConstructor("AmbName", 1);
	
	public AmbName(String name) {
		super(CTOR, new IStrategoTerm[] { new BasicStrategoString(name) });
	}

}

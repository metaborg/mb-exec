package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoAppl;
import org.spoofax.terms.StrategoConstructor;

public class DottedName extends StrategoAppl {
    
    private static final long serialVersionUID = 1L;
    
    private final static IStrategoConstructor CTOR = new StrategoConstructor("DottedName", 1);
	
	protected DottedName(String name) {
		super(CTOR, new IStrategoTerm[] { new ECJString(name) });
	}

}

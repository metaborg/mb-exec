package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoAppl;
import org.spoofax.terms.StrategoConstructor;
import org.spoofax.terms.TermFactory;

public class AmbName extends StrategoAppl {

    private static final long serialVersionUID = 1L;
    
    private static IStrategoConstructor CTOR = new StrategoConstructor("AmbName", 1);
	
	public AmbName(String name) {
		super(CTOR, 
		        new IStrategoTerm[] { new ECJString(name) },
		        TermFactory.EMPTY_LIST,
		        IStrategoTerm.IMMUTABLE);
	}

}

package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.Attribute;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMAttribute extends AbstractASMNode {

	private static final long serialVersionUID = -5661638607562559846L;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("Attribute", 2);
	private final Attribute wrappee;
	
	ASMAttribute(Attribute wrappee) {
		super(CTOR);
		this.wrappee = wrappee;
	}

	@Override
	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrap(wrappee.type);
		case 1:
			return ASMFactory.wrap(wrappee.isCodeAttribute());
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

}

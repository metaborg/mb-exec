package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.Label;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedLabel extends WrappedASMNode {

	private final Label wrappee;
	private static final IStrategoConstructor CTOR = new ASMConstructor("Label", 1);

	public WrappedLabel(Label node) {
		super(CTOR);
		this.wrappee = node;
	}

	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrap(-1); // FIXME wrappee.getOffset());
		}
		throw new ArrayIndexOutOfBoundsException();
	}

	public Label getWrappee() {
		return wrappee;
	}
}

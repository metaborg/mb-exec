package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.LabelNode;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedLabelNode extends WrappedASMNode {

	private final LabelNode wrappee;
	private static final IStrategoConstructor CTOR = new ASMConstructor("LabelNode", 3);
	
	public WrappedLabelNode(LabelNode node) {
		super(CTOR);
		this.wrappee = node;
	}

	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrap(wrappee.getOpcode());
		case 1:
			return ASMFactory.wrap(wrappee.getType());
		case 2:
			return ASMFactory.wrap(wrappee.getLabel());
		}
		throw new NotImplementedException();
	}

}

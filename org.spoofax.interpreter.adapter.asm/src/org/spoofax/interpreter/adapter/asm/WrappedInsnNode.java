package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.InsnNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedInsnNode extends WrappedASMNode {

	private final InsnNode wrappee;
	private static final IStrategoConstructor CTOR = new ASMConstructor("InsnNode", 2);

	public WrappedInsnNode(InsnNode node) {
		super(CTOR);
		this.wrappee = node;
	}

	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrap(wrappee.getOpcode());
		case 1:
			return ASMFactory.wrap(wrappee.getType());
			
		}
		throw new ArrayIndexOutOfBoundsException();
	}

}

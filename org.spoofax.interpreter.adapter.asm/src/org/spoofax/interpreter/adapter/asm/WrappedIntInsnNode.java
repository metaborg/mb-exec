package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.IntInsnNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedIntInsnNode extends WrappedASMNode {

	
	private final IntInsnNode wrappee;
	private static final IStrategoConstructor CTOR = new ASMConstructor("IntInsnNode", 2);

	public WrappedIntInsnNode(IntInsnNode node) {
		super(CTOR);
		this.wrappee = node;
	}

	public IStrategoTerm getSubterm(int index) {
		switch (index) {
		case 0:
			return ASMFactory.wrap(wrappee.getOpcode());
		case 1:
			return ASMFactory.wrap(wrappee.getType());
		}
		throw new ArrayIndexOutOfBoundsException();
	}

}

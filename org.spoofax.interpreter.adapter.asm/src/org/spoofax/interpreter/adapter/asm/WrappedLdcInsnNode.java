package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.LdcInsnNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedLdcInsnNode extends WrappedASMNode {

	
	private final LdcInsnNode wrappee;
	private static final IStrategoConstructor CTOR = new ASMConstructor("LdcInsnNode", 2);

	public WrappedLdcInsnNode(LdcInsnNode node) {
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

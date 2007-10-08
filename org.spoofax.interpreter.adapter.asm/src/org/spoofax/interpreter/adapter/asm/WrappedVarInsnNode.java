package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.VarInsnNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedVarInsnNode extends WrappedASMNode {

	private final VarInsnNode wrappee;
	private static final IStrategoConstructor CTOR = new ASMConstructor("VarInsnNode",3);

	public WrappedVarInsnNode(VarInsnNode node) {
		super(CTOR);
		this.wrappee = node;
	}

	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrap(wrappee.var);
		case 1:
			return ASMFactory.wrap(wrappee.getOpcode());
		case 2:
			return ASMFactory.wrap(wrappee.getType());
		}
		throw new ArrayIndexOutOfBoundsException();
	}

}

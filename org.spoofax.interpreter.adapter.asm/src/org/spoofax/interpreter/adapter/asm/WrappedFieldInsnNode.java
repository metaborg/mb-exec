package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.FieldInsnNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedFieldInsnNode extends WrappedASMNode {

	private final FieldInsnNode wrappee;
	private static final IStrategoConstructor CTOR = new ASMConstructor("FieldInsnNode", 2);

	public WrappedFieldInsnNode(FieldInsnNode node) {
		super(CTOR);
		this.wrappee = node;
	}

	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrap(wrappee.desc);
		case 1:
			return ASMFactory.wrap(wrappee.name);
		case 2:
			return ASMFactory.wrap(wrappee.owner);
		case 3:
			return ASMFactory.wrap(wrappee.getOpcode());
		case 4:
			return ASMFactory.wrap(wrappee.getType());
		}
		throw new ArrayIndexOutOfBoundsException();
	}

}

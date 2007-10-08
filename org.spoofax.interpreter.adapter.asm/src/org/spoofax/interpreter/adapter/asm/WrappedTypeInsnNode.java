package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.TypeInsnNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedTypeInsnNode extends WrappedASMNode {

	private final TypeInsnNode wrappee;
	private static final IStrategoConstructor CTOR = new ASMConstructor("TypeInsnNode", 3);

	public WrappedTypeInsnNode(TypeInsnNode node) {
		super(CTOR);
		this.wrappee = node;
	}

	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrap(wrappee.desc);
		case 1:
			return ASMFactory.wrap(wrappee.getOpcode());
		case 2:
			return ASMFactory.wrap(wrappee.getType());
		}
		throw new ArrayIndexOutOfBoundsException();
	}

}

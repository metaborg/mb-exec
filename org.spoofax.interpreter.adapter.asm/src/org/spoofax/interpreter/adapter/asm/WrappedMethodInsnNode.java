package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.MethodInsnNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedMethodInsnNode extends WrappedASMNode {
	
	private final static IStrategoConstructor CTOR = new ASMConstructor("MethodInsnNode", 5); 
	private final MethodInsnNode wrappee;	
	
	public WrappedMethodInsnNode(MethodInsnNode node) {
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

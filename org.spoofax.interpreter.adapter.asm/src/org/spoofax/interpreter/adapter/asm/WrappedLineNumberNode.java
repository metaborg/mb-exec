package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.LineNumberNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedLineNumberNode extends WrappedASMNode {

	private final LineNumberNode wrappee;
	private static final IStrategoConstructor CTOR = new ASMConstructor("LineNumberNode", 4);
	
	public WrappedLineNumberNode(LineNumberNode node) {
		super(CTOR);
		this.wrappee = node;
	}

	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrap(wrappee.line);
		case 1:
			return ASMFactory.wrap(wrappee.start);
		case 2:
			return ASMFactory.wrap(wrappee.getOpcode());
		case 3:
			return ASMFactory.wrap(wrappee.getType());
		}
		throw new ArrayIndexOutOfBoundsException();
	}

}

package org.spoofax.interpreter.adapter.asm;

import java.util.List;

import org.objectweb.asm.tree.AnnotationNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMAnnotationNode extends WrappedASMNode {

	private static final IStrategoConstructor CTOR = new StrategoConstructor("AnnotationNode", 1);
	private final AnnotationNode wrappee;
	
	ASMAnnotationNode(AnnotationNode wrappee) {
		super(CTOR);
		this.wrappee = wrappee;
	}

	private static final long serialVersionUID = 2411253571345478458L;

	@SuppressWarnings("unchecked")
	@Override
	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrap(wrappee.desc);
		case 1:
			return ASMFactory.wrap((List<Object>)wrappee.values);
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

}

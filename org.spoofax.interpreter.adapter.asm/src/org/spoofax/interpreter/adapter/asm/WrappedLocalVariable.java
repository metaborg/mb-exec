package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.LocalVariableNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.sun.jmx.remote.internal.ArrayNotificationBuffer;

public class WrappedLocalVariable extends WrappedASMNode {

	private LocalVariableNode wrappee;
	private static final IStrategoConstructor CTOR = new ASMConstructor("LocalVariable", 6); 
	
	public WrappedLocalVariable(LocalVariableNode node) {
		super(CTOR);
		this.wrappee = node;
	}

	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ASMFactory.wrap(wrappee.desc);
		case 1:
			return ASMFactory.wrap(wrappee.index);
		case 2:
			return ASMFactory.wrap(wrappee.name);
		case 3: 
			return ASMFactory.wrap(wrappee.signature);
		case 4: 
			return ASMFactory.wrap(wrappee.start);
		case 5: 
			return ASMFactory.wrap(wrappee.end);
		}
		throw new ArrayIndexOutOfBoundsException();
	}


}

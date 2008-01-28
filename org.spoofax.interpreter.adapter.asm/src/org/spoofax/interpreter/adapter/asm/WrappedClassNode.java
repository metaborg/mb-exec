package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.ClassNode;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedClassNode extends WrappedASMNode {

	private final ClassNode wrappee;
	private final static IStrategoConstructor CTOR = new ASMConstructor("ClassNode", 17);
	
	WrappedClassNode(final ClassNode wrappee) {
		super(CTOR);
		this.wrappee = wrappee;
	}

	@SuppressWarnings("unchecked")
	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0: 
			return ASMFactory.wrap(wrappee.access);
		case 1: 
			return ASMFactory.wrap(wrappee.attrs);
		case 2: 
			return ASMFactory.wrap(wrappee.fields);
		case 3: 
			return ASMFactory.wrap(wrappee.innerClasses);
		case 4: 
			return ASMFactory.wrap(wrappee.interfaces);
		case 5: 
			return ASMFactory.wrap(wrappee.invisibleAnnotations);
		case 6: 
			return ASMFactory.wrap(wrappee.methods);
		case 7: 
			return ASMFactory.wrap(wrappee.name);
		case 8: 
			return ASMFactory.wrap(wrappee.outerClass);
		case 9: 
			return ASMFactory.wrap(wrappee.outerMethod);
		case 10: 
			return ASMFactory.wrap(wrappee.outerMethodDesc);
		case 11: 
			return ASMFactory.wrap(wrappee.signature);
		case 12: 
			return ASMFactory.wrap(wrappee.sourceDebug);
		case 13: 
			return ASMFactory.wrap(wrappee.sourceFile);
		case 14: 
			return ASMFactory.wrap(wrappee.superName);
		case 15: 
			return ASMFactory.wrap(wrappee.version);
		case 16: 
			return ASMFactory.wrap(wrappee.visibleAnnotations);
		}
		throw new ArrayIndexOutOfBoundsException();
	}

	public boolean match(IStrategoTerm second) {
		throw new NotImplementedException();
	}
}

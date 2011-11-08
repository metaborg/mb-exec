package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedITypeHierarchy extends AbstractECJAppl {

    private static final long serialVersionUID = 1L;

    private final static IStrategoConstructor CTOR = new ECJConstructor("ITypeHierarchy", 2);
	private final ITypeHierarchy wrappee;
	
	protected WrappedITypeHierarchy(ITypeHierarchy wrappee) {
		super(CTOR);
		this.wrappee = wrappee;
	}

	@Override
	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0: 
			return new WrappedIType(wrappee.getType());
		case 1:
			final IType[] types = wrappee.getAllSubtypes(wrappee.getType());
			final IStrategoTerm[] terms = new IStrategoTerm[types.length];
			for(int i = 0; i < types.length; i++)
				terms[i] = ECJFactory.wrap(types[i]);
			return new ECJGenericList(terms);
		default:
			throw new ArrayIndexOutOfBoundsException();
		}
	}

}

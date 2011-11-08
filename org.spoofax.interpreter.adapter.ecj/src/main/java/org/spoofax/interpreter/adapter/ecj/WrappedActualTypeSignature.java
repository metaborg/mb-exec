package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.Signature;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedActualTypeSignature extends AbstractECJAppl {
    
    private static final long serialVersionUID = 1L;

	private final String wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("ActualTypeSignature", 4);

	protected WrappedActualTypeSignature(String signature) {
		super(CTOR);
		wrappee = signature;

	}

	@Override
	public IStrategoTerm getSubterm(int index) {
		switch(index) {
		case 0:
			return ECJFactory.wrap(Signature.getSignatureQualifier(wrappee));
		case 1: 
			return ECJFactory.wrap(Signature.getSignatureSimpleName(wrappee));
		case 2: {
			final String te = Signature.getTypeErasure(wrappee);
			final String p = Signature.getSignatureQualifier(te);
			final String n = Signature.getSignatureSimpleName(te);
			return ECJFactory.wrap(p.length() == 0 ? n : p + "." + n);
		}
		case 3:
			return ECJFactory.wrapSignatures(Signature.getTypeArguments(wrappee));
		default:
			throw new ArrayIndexOutOfBoundsException();
		}
	}

}

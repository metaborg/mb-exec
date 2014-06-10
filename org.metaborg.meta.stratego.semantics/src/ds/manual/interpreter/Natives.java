package ds.manual.interpreter;

import org.metaborg.meta.interpreter.framework.INodeList;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

import ds.generated.interpreter.I_Node;

public class Natives {

	public static IStrategoTerm asATerm_1(I_Node t) {
		throw new RuntimeException("Not implemented");
	}

	public static boolean isATermInt_1(IStrategoTerm t) {
		throw new RuntimeException("Not implemented");
	}

	public static IStrategoInt asATermInt_1(IStrategoTerm t) {
		throw new RuntimeException("Not implemented");
	}

	public static boolean isATermString_1(IStrategoTerm t) {
		throw new RuntimeException("Not implemented");
	}

	public static IStrategoString asATermString_1(IStrategoTerm t) {
		throw new RuntimeException("Not implemented");
	}

	public static boolean isATermAppl_1(IStrategoTerm t) {
		throw new RuntimeException("Not implemented");
	}

	public static IStrategoAppl asATermAppl_1(IStrategoTerm t) {
		throw new RuntimeException("Not implemented");
	}

	public static INodeList<IStrategoTerm> getAllSubterms_1(IStrategoAppl aappl) {
		throw new RuntimeException("Not implemented");
	}

	public static IStrategoList makeList_2(ITermFactory tf,
			INodeList<IStrategoTerm> subterms) {
		throw new RuntimeException("Not implemented");
	}

}

package ds.manual.interpreter;

import org.metaborg.meta.interpreter.framework.AValue;
import org.metaborg.meta.interpreter.framework.INodeList;
import org.metaborg.meta.interpreter.framework.PersistentMap;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

import ds.generated.interpreter.I_Node;
import ds.generated.interpreter.I_STerm;
import ds.generated.interpreter.I_Strategy;

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

	public static INodeList<IStrategoTerm> getAllSubterms_1(IStrategoTerm aappl) {
		throw new RuntimeException("Not implemented");
	}

	public static IStrategoList makeList_2(ITermFactory tf,
			INodeList<IStrategoTerm> subterms) {
		throw new RuntimeException("Not implemented");
	}

	public static boolean isATermList_1(IStrategoTerm t) {
		throw new RuntimeException("Not implemented");
	}

	public static IStrategoList asATermList_1(IStrategoTerm t) {
		throw new RuntimeException("Not implemented");
	}

	public static int length_1(INodeList<I_STerm> ts) {
		return ts.size();
	}

	public static INodeList<IStrategoTerm> toNativeList_1(IStrategoList ats_) {
		throw new RuntimeException("Not implemented");
	}

	public static IStrategoAppl makeAppl_3(ITermFactory tf,
			IStrategoConstructor constr, INodeList<IStrategoTerm> ts_) {
		throw new RuntimeException("Not implemented");
	}

	public static boolean isATermTuple_1(IStrategoTerm t) {
		throw new RuntimeException("Not implemented");
	}

	public static AValue primCall_3(String name, INodeList<I_Strategy> ass,
			INodeList<IStrategoTerm> ats_aterms) {
		throw new RuntimeException("Not implemented");
	}

	public static INodeList<IStrategoTerm> asNILofT_1(
			INodeList<I_Node> tmpbuild861) {
		throw new RuntimeException("Not implemented");
	}

	public static PersistentMap<Object, Object> asD_1(
			PersistentMap<Object, Object> persistentMap) {
		throw new RuntimeException("Not implemented");
	}

	public static INodeList<String> asNILofString_1(
			INodeList<I_Node> tmpbuild1437) {
		throw new RuntimeException("Not implemented");
	}

	public static IStrategoTerm[] List2TARRAY_1(INodeList<IStrategoTerm> ts_) {
		throw new RuntimeException("Not implemented");
	}

}

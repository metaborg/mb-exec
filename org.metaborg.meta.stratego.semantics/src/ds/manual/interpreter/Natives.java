package ds.manual.interpreter;

import org.metaborg.meta.interpreter.framework.AValue;
import org.metaborg.meta.interpreter.framework.INodeList;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

import com.github.krukow.clj_ds.PersistentMap;

import ds.generated.interpreter.I_Node;
import ds.generated.interpreter.I_STerm;
import ds.generated.interpreter.I_Strategy;

public class Natives {

	public static IStrategoTerm asATerm_1(I_Node t) {
		throw new RuntimeException("Not implemented");
	}

	public static boolean isATermInt_1(IStrategoTerm t) {
		return t.getTermType() == IStrategoTerm.INT;
	}

	public static IStrategoInt asATermInt_1(IStrategoTerm t) {
		return (IStrategoInt) t;
	}

	public static boolean isATermString_1(IStrategoTerm t) {
		return t.getTermType() == IStrategoTerm.STRING;
	}

	public static IStrategoString asATermString_1(IStrategoTerm t) {
		return (IStrategoString) t;
	}

	public static boolean isATermAppl_1(IStrategoTerm t) {
		return t.getTermType() == IStrategoString.APPL;
	}

	public static IStrategoAppl asATermAppl_1(IStrategoTerm t) {
		return (IStrategoAppl) t;
	}

	public static boolean isATermList_1(IStrategoTerm t) {
		return t.getTermType() == IStrategoTerm.LIST;
	}

	public static IStrategoList asATermList_1(IStrategoTerm t) {
		return (IStrategoList) t;
	}

	public static int length_1(INodeList<I_STerm> ts) {
		return ts.size();
	}

	public static boolean isATermTuple_1(IStrategoTerm t) {
		return t.getTermType() == IStrategoTerm.TUPLE;
	}

	public static AValue primCall_3(String name, INodeList<I_Strategy> ass, INodeList<IStrategoTerm> ats_aterms) {
		throw new RuntimeException("Primitive calls not supported. Attempted call to: " + name);
	}

	@SuppressWarnings("unchecked")
	public static INodeList<IStrategoTerm> asNILofT_1(INodeList<?> l) {
		return (INodeList<IStrategoTerm>) l;
	}

	@SuppressWarnings("unchecked")
	public static INodeList<String> asNILofString_1(INodeList<?> l) {
		return (INodeList<String>) l;
	}

	public static IStrategoTerm[] List2TARRAY_1(INodeList<IStrategoTerm> ts_) {
		int size = ts_.size();
		IStrategoTerm[] clone = new IStrategoTerm[size];
		INodeList<IStrategoTerm> list = ts_;
		for (int i = 0; i < size; i++) {
			clone[i] = list.head();
			list = list.tail();
		}
		return clone;
	}

	public static PersistentMap<Object, Object> asD_1(PersistentMap<Object, Object> map) {
		return map;
	}

	public static boolean isNil_1(Object v) {
		return v == null;
	}

	public static IStrategoList makeNil_1(ITermFactory tf) {
		return tf.makeList();
	}

	public static int parseInt_1(String s) {
		return Integer.parseInt(s);
	}

	public static String createAnonymousName_1(String x) {
		return "<anon_" + x + "_" + (counter++) + ">";
	}

	private static int counter = 0;

}

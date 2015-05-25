package ds.manual.interpreter;

import java.io.IOException;

import org.metaborg.meta.interpreter.framework.INodeList;
import org.spoofax.interpreter.core.StackTracer;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.io.binary.TermReader;

import ds.generated.interpreter.L_T;

public class Natives {

	public static int length_1(INodeList ts) {
		return ts.size();
	}

	public static IStrategoTerm importTerm_2(AutoInterpInteropContext context,
			String p) {
		try {
			return new TermReader(context.getFactory())
					.parseFromStream(Natives.class.getClassLoader()
							.getResourceAsStream(p));
		} catch (IOException e) {
			throw new org.metaborg.meta.interpreter.framework.InterpreterException(
					"Import term failed", e);
		}
	}

	public static IStrategoTerm[] List2TARRAY_1(L_T ts_) {
		int size = ts_.size();
		IStrategoTerm[] clone = new IStrategoTerm[size];
		L_T list = ts_;
		for (int i = 0; i < size; i++) {
			clone[i] = list.head();
			list = list.tail();
		}
		return clone;
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

	public static boolean isQuotedString_1(String s) {
		return s.length() > 1 && s.charAt(0) == '"';
	}

	public static String unquoteString_1(String s) {
		if (!isQuotedString_1(s)) {
			return s;
		}

		return s.substring(1, s.length() - 1);
	}

	private static int fresh_counter = 1;

	public static int fresh() {
		if (fresh_counter == Integer.MAX_VALUE) {
			throw new RuntimeException("Fresh counter overflow");
		}
		return fresh_counter++;
	}

	public static boolean isEmptyStrategies_1(INodeList sss) {
		return sss.isEmpty();
	}

	public static boolean isEmpty_1(INodeList ass) {
		return ass.isEmpty();
	}

	public static boolean boolAnd_2(boolean b1, boolean b2) {
		return b1 && b2;
	}

	public static boolean boolOr_2(boolean b1, boolean b2) {
		return b1 || b2;
	}

	public static double parseReal_1(String s) {
		return Double.parseDouble(s);
	}

	public static boolean pushTraceElem_2(String s, StackTracer t) {
		t.push(s);
		return true;
	}

	public static boolean popOnFailure_1(StackTracer t) {
		t.popOnFailure();
		return true;
	}

	public static boolean popOnSuccess_1(StackTracer t) {
		t.popOnSuccess();
		return true;
	}

}

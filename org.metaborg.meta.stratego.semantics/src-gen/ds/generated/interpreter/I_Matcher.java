package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public interface I_Matcher extends IMatchableNode {
	public R_ma_Value exec_ma(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _3,
			org.spoofax.interpreter.terms.ITermFactory _4,
			org.spoofax.interpreter.terms.IStrategoTerm _5,
			ds.manual.interpreter.SState _6, ds.manual.interpreter.VState _7,
			boolean _8, org.spoofax.interpreter.core.StackTracer _9);
}
package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public interface I_LetHelper extends IMatchableNode {
	public R_leteval_Value exec_leteval(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			org.spoofax.interpreter.terms.IStrategoTerm _2,
			org.spoofax.interpreter.terms.ITermFactory _3,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _4,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _5,
			ds.manual.interpreter.SState _6, ds.manual.interpreter.VState _7,
			boolean _8, org.spoofax.interpreter.core.StackTracer _9);
}
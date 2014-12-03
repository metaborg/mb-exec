package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public interface I_Somer extends IMatchableNode {
	public R_some_TLIST exec_some(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _3,
			org.spoofax.interpreter.terms.ITermFactory _4,
			org.spoofax.interpreter.terms.IStrategoTerm _5,
			ds.manual.interpreter.SState _6, ds.manual.interpreter.VState _7,
			org.spoofax.interpreter.core.StackTracer _8, boolean _9);
}
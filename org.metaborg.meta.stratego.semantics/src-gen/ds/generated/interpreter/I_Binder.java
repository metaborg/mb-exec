package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public interface I_Binder extends IMatchableNode {
	public R_bindtvars_VEnv exec_bindtvars(
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
			ds.manual.interpreter.VState _2);

	public R_bindsvars_SEnv exec_bindsvars(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			org.spoofax.interpreter.terms.IStrategoTerm _2,
			org.spoofax.interpreter.terms.ITermFactory _3,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _4,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _5,
			boolean _6, org.spoofax.interpreter.core.StackTracer _7,
			ds.manual.interpreter.VState _8, ds.manual.interpreter.SState _9);
}
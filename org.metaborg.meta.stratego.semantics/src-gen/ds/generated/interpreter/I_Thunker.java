package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public interface I_Thunker extends IMatchableNode {
	public R_thunks_List_Thunk_ exec_thunks(
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			ds.manual.interpreter.SState _3);

	public R_thunk_Thunk exec_thunk(
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			ds.manual.interpreter.SState _3);
}
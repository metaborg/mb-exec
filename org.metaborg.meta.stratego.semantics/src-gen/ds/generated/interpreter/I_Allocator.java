package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public interface I_Allocator extends IMatchableNode {
	public R_allocmodule_SEnv exec_allocmodule(
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			ds.manual.interpreter.SState _3);

	public R_allocsdefs_AllocatorResult exec_allocsdefs(
			ds.manual.interpreter.SState _1);

	public R_storesdefs_Bool exec_storesdefs(
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			ds.manual.interpreter.SState _3);
}
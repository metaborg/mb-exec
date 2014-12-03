package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class R_allocmodule_SEnv {
	public final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> value;

	public ds.manual.interpreter.SState _1;

	public R_allocmodule_SEnv(
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> value,
			ds.manual.interpreter.SState _1) {
		this.value = value;
		this._1 = _1;
	}

	public ds.manual.interpreter.SState get_1() {
		return this._1;
	}
}
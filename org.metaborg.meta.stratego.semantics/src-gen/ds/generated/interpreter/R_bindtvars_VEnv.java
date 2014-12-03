package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class R_bindtvars_VEnv {
	public final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> value;

	public ds.manual.interpreter.VState _1;

	public R_bindtvars_VEnv(
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> value,
			ds.manual.interpreter.VState _1) {
		this.value = value;
		this._1 = _1;
	}

	public ds.manual.interpreter.VState get_1() {
		return this._1;
	}
}
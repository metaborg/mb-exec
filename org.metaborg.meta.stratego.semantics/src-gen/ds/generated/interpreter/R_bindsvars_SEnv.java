package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class R_bindsvars_SEnv {
	public final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> value;

	public boolean _1;

	public org.spoofax.interpreter.core.StackTracer _2;

	public ds.manual.interpreter.VState _3;

	public ds.manual.interpreter.SState _4;

	public R_bindsvars_SEnv(
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> value,
			boolean _1, org.spoofax.interpreter.core.StackTracer _2,
			ds.manual.interpreter.VState _3, ds.manual.interpreter.SState _4) {
		this.value = value;
		this._1 = _1;
		this._2 = _2;
		this._3 = _3;
		this._4 = _4;
	}

	public boolean get_1() {
		return this._1;
	}

	public org.spoofax.interpreter.core.StackTracer get_2() {
		return this._2;
	}

	public ds.manual.interpreter.VState get_3() {
		return this._3;
	}

	public ds.manual.interpreter.SState get_4() {
		return this._4;
	}
}
package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class R_default_Value {
	public final IValue value;

	public ds.manual.interpreter.SState _1;

	public ds.manual.interpreter.VState _2;

	public boolean _3;

	public org.spoofax.interpreter.core.StackTracer _4;

	public R_default_Value(IValue value, ds.manual.interpreter.SState _1,
			ds.manual.interpreter.VState _2, boolean _3,
			org.spoofax.interpreter.core.StackTracer _4) {
		this.value = value;
		this._1 = _1;
		this._2 = _2;
		this._3 = _3;
		this._4 = _4;
	}

	public ds.manual.interpreter.SState get_1() {
		return this._1;
	}

	public ds.manual.interpreter.VState get_2() {
		return this._2;
	}

	public boolean get_3() {
		return this._3;
	}

	public org.spoofax.interpreter.core.StackTracer get_4() {
		return this._4;
	}
}
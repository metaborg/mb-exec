package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class R_some_TLIST {
	public final org.spoofax.interpreter.terms.IStrategoList value;

	public ds.manual.interpreter.SState _1;

	public ds.manual.interpreter.VState _2;

	public org.spoofax.interpreter.core.StackTracer _3;

	public boolean _4;

	public R_some_TLIST(org.spoofax.interpreter.terms.IStrategoList value,
			ds.manual.interpreter.SState _1, ds.manual.interpreter.VState _2,
			org.spoofax.interpreter.core.StackTracer _3, boolean _4) {
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

	public org.spoofax.interpreter.core.StackTracer get_3() {
		return this._3;
	}

	public boolean get_4() {
		return this._4;
	}
}
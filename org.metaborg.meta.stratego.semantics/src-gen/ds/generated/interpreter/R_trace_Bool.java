package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class R_trace_Bool {
	public final boolean value;

	public org.spoofax.interpreter.core.StackTracer _1;

	public R_trace_Bool(boolean value,
			org.spoofax.interpreter.core.StackTracer _1) {
		this.value = value;
		this._1 = _1;
	}

	public org.spoofax.interpreter.core.StackTracer get_1() {
		return this._1;
	}
}
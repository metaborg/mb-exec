package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class pushTrace_1 extends AbstractNode implements I_TraceOp {
	private boolean hasSpecialized;

	public String _1;

	public pushTrace_1(INodeSource source, String _1) {
		this.setSourceInfo(source);
		this._1 = _1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final pushTrace_1 other = (pushTrace_1) obj;
		if (_1 == null) {
			if (other._1 != null) {
				return false;
			}
		} else if (!_1.equals(other._1)) {
			return false;
		}
		return true;
	}

	@Override
	public void specializeChildren(int depth) {
		if (!hasSpecialized) {
			hasSpecialized = true;
		}
	}

	public R_default_Value exec_default(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _3,
			org.spoofax.interpreter.terms.IStrategoTerm _4,
			org.spoofax.interpreter.terms.ITermFactory _5,
			ds.manual.interpreter.SState _6, ds.manual.interpreter.VState _7,
			boolean _8, org.spoofax.interpreter.core.StackTracer _9) {
		this.specializeChildren(0);
		throw new InterpreterException("Rule failure");
	}

	public R_trace_Bool exec_trace(org.spoofax.interpreter.core.StackTracer _1) {
		this.specializeChildren(0);
		final org.spoofax.interpreter.core.StackTracer trace_in2910 = _1;
		final String lifted_29857 = this._1;
		final org.spoofax.interpreter.core.StackTracer trace_out2910 = trace_in2910;
		final boolean result_out4548 = true;
		return new R_trace_Bool(result_out4548, trace_out2910);
	}

	public String get_1() {
		return this._1;
	}
}
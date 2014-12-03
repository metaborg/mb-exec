package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class popTrace_1 extends AbstractNode implements I_TraceOp {
	private boolean hasSpecialized;

	public IValue _1;

	public popTrace_1(INodeSource source, IValue _1) {
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
		final popTrace_1 other = (popTrace_1) obj;
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
		final org.spoofax.interpreter.core.StackTracer trace_in2911 = _1;
		final IValue v4131 = this._1;
		{
			final F_0 $tmp2704 = v4131.match(F_0.class);
			if ($tmp2704 != null) {
				final boolean lifted_29897 = ds.manual.interpreter.Natives
						.popOnFailure_1(trace_in2911);
				final org.spoofax.interpreter.core.StackTracer trace_out2911 = trace_in2911;
				final boolean result_out4549 = true;
				return new R_trace_Bool(result_out4549, trace_out2911);
			} else {
				final S_1 $tmp2705 = v4131.match(S_1.class);
				if ($tmp2705 != null) {
					final org.spoofax.interpreter.terms.IStrategoTerm lifted_29907 = $tmp2705
							.get_1();
					final boolean lifted_29917 = ds.manual.interpreter.Natives
							.popOnSuccess_1(trace_in2911);
					final org.spoofax.interpreter.core.StackTracer trace_out2911 = trace_in2911;
					final boolean result_out4549 = true;
					return new R_trace_Bool(result_out4549, trace_out2911);
				} else {
				}
			}
		}
		{
			throw new InterpreterException("Rule failure");
		}
	}

	public IValue get_1() {
		return this._1;
	}
}
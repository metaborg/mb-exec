package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class tracing_2 extends AbstractNode implements I_TraceOp {
	private boolean hasSpecialized;

	public String _1;

	@Child
	public I_Strategy _2;

	public tracing_2(INodeSource source, String _1, I_Strategy _2) {
		this.setSourceInfo(source);
		this._1 = _1;
		this._2 = adoptChild(_2);
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
		final tracing_2 other = (tracing_2) obj;
		if (_1 == null) {
			if (other._1 != null) {
				return false;
			}
		} else if (!_1.equals(other._1)) {
			return false;
		}
		if (_2 == null) {
			if (other._2 != null) {
				return false;
			}
		} else if (!_2.equals(other._2)) {
			return false;
		}
		return true;
	}

	@Override
	public void specializeChildren(int depth) {
		if (!hasSpecialized) {
			if (_2 instanceof IGenericNode) {
				((IGenericNode) _2).specialize(depth);
			}
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2729 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3093 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3184 = _3;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2729 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2729 = _5;
		final ds.manual.interpreter.SState sheap_in3639 = _6;
		final ds.manual.interpreter.VState vheap_in3275 = _7;
		final boolean bool_in2729 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2909 = _9;
		final String name2465 = this._1;
		final I_Strategy s11444 = this._2;
		final I_TraceOp lifted_30917 = new pushTrace_1(null, name2465);
		final R_trace_Bool $tmp2701 = lifted_30917.exec_trace(trace_in2909);
		final boolean lifted_29777 = $tmp2701.value;
		final org.spoofax.interpreter.core.StackTracer trace_28560 = $tmp2701
				.get_1();
		final R_default_Value $tmp2702 = s11444.exec_default(ic_in2729,
				senv_in3093, venv_in3184, t_in2729, tf_in2729, sheap_in3639,
				vheap_in3275, bool_in2729, trace_28560);
		final IValue v4130 = $tmp2702.value;
		final ds.manual.interpreter.SState sheap_29589 = $tmp2702.get_1();
		final ds.manual.interpreter.VState vheap_28938 = $tmp2702.get_2();
		final boolean bool_28497 = $tmp2702.get_3();
		final org.spoofax.interpreter.core.StackTracer trace_33695 = $tmp2702
				.get_4();
		final I_TraceOp lifted_30927 = new popTrace_1(null, v4130);
		final R_trace_Bool $tmp2703 = lifted_30927.exec_trace(trace_33695);
		final boolean lifted_29787 = $tmp2703.value;
		final org.spoofax.interpreter.core.StackTracer trace_4419 = $tmp2703
				.get_1();
		final ds.manual.interpreter.SState sheap_out3639 = sheap_29589;
		final ds.manual.interpreter.VState vheap_out3275 = vheap_28938;
		final boolean bool_out2729 = bool_28497;
		final org.spoofax.interpreter.core.StackTracer trace_out2909 = trace_4419;
		final IValue result_out4547 = v4130;
		return new R_default_Value(result_out4547, sheap_out3639,
				vheap_out3275, bool_out2729, trace_out2909);
	}

	public R_trace_Bool exec_trace(org.spoofax.interpreter.core.StackTracer _1) {
		this.specializeChildren(0);
		throw new InterpreterException("Rule failure");
	}

	public String get_1() {
		return this._1;
	}

	public I_Strategy get_2() {
		return this._2;
	}
}
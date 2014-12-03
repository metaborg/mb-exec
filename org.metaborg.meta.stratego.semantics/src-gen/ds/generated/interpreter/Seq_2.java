package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Seq_2 extends AbstractNode implements I_Strategy {
	private boolean hasSpecialized;

	@Child
	public I_Strategy _1;

	@Child
	public I_Strategy _2;

	public Seq_2(INodeSource source, I_Strategy _1, I_Strategy _2) {
		this.setSourceInfo(source);
		this._1 = adoptChild(_1);
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
		final Seq_2 other = (Seq_2) obj;
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
			if (_1 instanceof IGenericNode) {
				((IGenericNode) _1).specialize(depth);
			}
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2706 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3066 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3156 = _3;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2706 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2706 = _5;
		final ds.manual.interpreter.SState sheap_in3606 = _6;
		final ds.manual.interpreter.VState vheap_in3246 = _7;
		final boolean bool_in2706 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2886 = _9;
		final I_Strategy s1863 = this._1;
		final I_Strategy lifted_29817 = this._2;
		{
			final R_default_Value $tmp2488 = s1863.exec_default(ic_in2706,
					senv_in3066, venv_in3156, t_in2706, tf_in2706,
					sheap_in3606, vheap_in3246, bool_in2706, trace_in2886);
			final IValue v1253 = $tmp2488.value;
			final ds.manual.interpreter.SState sheap_29537 = $tmp2488.get_1();
			final ds.manual.interpreter.VState vheap_28891 = $tmp2488.get_2();
			final boolean bool_28452 = $tmp2488.get_3();
			final org.spoofax.interpreter.core.StackTracer trace_28515 = $tmp2488
					.get_4();
			final S_1 $tmp2489 = v1253.match(S_1.class);
			if ($tmp2489 != null) {
				final org.spoofax.interpreter.terms.IStrategoTerm t_106 = $tmp2489
						.get_1();
				final R_default_Value $tmp2490 = lifted_29817.exec_default(
						ic_in2706, senv_in3066, venv_in3156, t_106, tf_in2706,
						sheap_29537, vheap_28891, bool_28452, trace_28515);
				final IValue v2496 = $tmp2490.value;
				final ds.manual.interpreter.SState sheap_34697 = $tmp2490
						.get_1();
				final ds.manual.interpreter.VState vheap_34231 = $tmp2490
						.get_2();
				final boolean bool_33356 = $tmp2490.get_3();
				final org.spoofax.interpreter.core.StackTracer trace_33675 = $tmp2490
						.get_4();
				final ds.manual.interpreter.SState sheap_out3606 = sheap_34697;
				final ds.manual.interpreter.VState vheap_out3246 = vheap_34231;
				final boolean bool_out2706 = bool_33356;
				final org.spoofax.interpreter.core.StackTracer trace_out2886 = trace_33675;
				final IValue result_out4506 = v2496;
				return new R_default_Value(result_out4506, sheap_out3606,
						vheap_out3246, bool_out2706, trace_out2886);
			} else {
				final F_0 $tmp2491 = v1253.match(F_0.class);
				if ($tmp2491 != null) {
					final ds.manual.interpreter.SState sheap_out3606 = sheap_29537;
					final ds.manual.interpreter.VState vheap_out3246 = vheap_28891;
					final boolean bool_out2706 = bool_28452;
					final org.spoofax.interpreter.core.StackTracer trace_out2886 = trace_28515;
					final IValue result_out4506 = v1253;
					return new R_default_Value(result_out4506, sheap_out3606,
							vheap_out3246, bool_out2706, trace_out2886);
				} else {
				}
			}
		}
		{
			throw new InterpreterException("Rule failure");
		}
	}

	public I_Strategy get_1() {
		return this._1;
	}

	public I_Strategy get_2() {
		return this._2;
	}
}
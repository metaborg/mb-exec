package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class GuardedLChoice_3 extends AbstractNode implements I_Strategy {
	private boolean hasSpecialized;

	@Child
	public I_Strategy _1;

	@Child
	public I_Strategy _2;

	@Child
	public I_Strategy _3;

	public GuardedLChoice_3(INodeSource source, I_Strategy _1, I_Strategy _2,
			I_Strategy _3) {
		this.setSourceInfo(source);
		this._1 = adoptChild(_1);
		this._2 = adoptChild(_2);
		this._3 = adoptChild(_3);
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
		final GuardedLChoice_3 other = (GuardedLChoice_3) obj;
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
		if (_3 == null) {
			if (other._3 != null) {
				return false;
			}
		} else if (!_3.equals(other._3)) {
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
			if (_3 instanceof IGenericNode) {
				((IGenericNode) _3).specialize(depth);
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2705 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3065 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3155 = _3;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2705 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2705 = _5;
		final ds.manual.interpreter.SState sheap_in3605 = _6;
		final ds.manual.interpreter.VState vheap_in3245 = _7;
		final boolean bool_in2705 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2885 = _9;
		final I_Strategy s1862 = this._1;
		final I_Strategy lifted_30497 = this._2;
		final I_Strategy s3160 = this._3;
		{
			final R_default_Value $tmp2483 = s1862.exec_default(ic_in2705,
					senv_in3065, venv_in3155, t_in2705, tf_in2705,
					sheap_in3605, vheap_in3245, bool_in2705, trace_in2885);
			final IValue lifted_33507 = $tmp2483.value;
			final ds.manual.interpreter.SState sheap_29536 = $tmp2483.get_1();
			final ds.manual.interpreter.VState vheap_28890 = $tmp2483.get_2();
			final boolean bool_28451 = $tmp2483.get_3();
			final org.spoofax.interpreter.core.StackTracer trace_28514 = $tmp2483
					.get_4();
			final S_1 $tmp2484 = lifted_33507.match(S_1.class);
			if ($tmp2484 != null) {
				final org.spoofax.interpreter.terms.IStrategoTerm t10083 = $tmp2484
						.get_1();
				final R_default_Value $tmp2485 = lifted_30497.exec_default(
						ic_in2705, senv_in3065, venv_in3155, t10083, tf_in2705,
						sheap_29536, vheap_28890, bool_28451, trace_28514);
				final IValue v2495 = $tmp2485.value;
				final ds.manual.interpreter.SState sheap_34695 = $tmp2485
						.get_1();
				final ds.manual.interpreter.VState vheap_34229 = $tmp2485
						.get_2();
				final boolean bool_33354 = $tmp2485.get_3();
				final org.spoofax.interpreter.core.StackTracer trace_33673 = $tmp2485
						.get_4();
				final ds.manual.interpreter.SState sheap_out3605 = sheap_34695;
				final ds.manual.interpreter.VState vheap_out3245 = vheap_34229;
				final boolean bool_out2705 = bool_33354;
				final org.spoofax.interpreter.core.StackTracer trace_out2885 = trace_33673;
				final IValue result_out4505 = v2495;
				return new R_default_Value(result_out4505, sheap_out3605,
						vheap_out3245, bool_out2705, trace_out2885);
			} else {
				final F_0 $tmp2486 = lifted_33507.match(F_0.class);
				if ($tmp2486 != null) {
					final R_default_Value $tmp2487 = s3160.exec_default(
							ic_in2705, senv_in3065, venv_in3155, t_in2705,
							tf_in2705, sheap_29536, vheap_28890, bool_28451,
							trace_28514);
					final IValue v3163 = $tmp2487.value;
					final ds.manual.interpreter.SState sheap_34696 = $tmp2487
							.get_1();
					final ds.manual.interpreter.VState vheap_34230 = $tmp2487
							.get_2();
					final boolean bool_33355 = $tmp2487.get_3();
					final org.spoofax.interpreter.core.StackTracer trace_33674 = $tmp2487
							.get_4();
					final ds.manual.interpreter.SState sheap_out3605 = sheap_34696;
					final ds.manual.interpreter.VState vheap_out3245 = vheap_34230;
					final boolean bool_out2705 = bool_33355;
					final org.spoofax.interpreter.core.StackTracer trace_out2885 = trace_33674;
					final IValue result_out4505 = v3163;
					return new R_default_Value(result_out4505, sheap_out3605,
							vheap_out3245, bool_out2705, trace_out2885);
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

	public I_Strategy get_3() {
		return this._3;
	}
}
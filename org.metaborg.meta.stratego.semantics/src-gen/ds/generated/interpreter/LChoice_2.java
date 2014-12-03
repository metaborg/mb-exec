package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class LChoice_2 extends AbstractNode implements I_Strategy {
	private boolean hasSpecialized;

	@Child
	public I_Strategy _1;

	@Child
	public I_Strategy _2;

	public LChoice_2(INodeSource source, I_Strategy _1, I_Strategy _2) {
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
		final LChoice_2 other = (LChoice_2) obj;
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2700 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3060 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3150 = _3;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2700 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2700 = _5;
		final ds.manual.interpreter.SState sheap_in3600 = _6;
		final ds.manual.interpreter.VState vheap_in3240 = _7;
		final boolean bool_in2700 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2880 = _9;
		final I_Strategy s1861 = this._1;
		final I_Strategy s2361 = this._2;
		{
			final R_default_Value $tmp2460 = s1861.exec_default(ic_in2700,
					senv_in3060, venv_in3150, t_in2700, tf_in2700,
					sheap_in3600, vheap_in3240, bool_in2700, trace_in2880);
			final IValue lifted_33467 = $tmp2460.value;
			final ds.manual.interpreter.SState sheap_29525 = $tmp2460.get_1();
			final ds.manual.interpreter.VState vheap_28879 = $tmp2460.get_2();
			final boolean bool_28440 = $tmp2460.get_3();
			final org.spoofax.interpreter.core.StackTracer trace_28503 = $tmp2460
					.get_4();
			final S_1 $tmp2461 = lifted_33467.match(S_1.class);
			if ($tmp2461 != null) {
				final org.spoofax.interpreter.terms.IStrategoTerm lifted_30467 = $tmp2461
						.get_1();
				final ds.manual.interpreter.SState sheap_out3600 = sheap_29525;
				final ds.manual.interpreter.VState vheap_out3240 = vheap_28879;
				final boolean bool_out2700 = bool_28440;
				final org.spoofax.interpreter.core.StackTracer trace_out2880 = trace_28503;
				final IValue result_out4500 = lifted_33467;
				return new R_default_Value(result_out4500, sheap_out3600,
						vheap_out3240, bool_out2700, trace_out2880);
			} else {
				final F_0 $tmp2462 = lifted_33467.match(F_0.class);
				if ($tmp2462 != null) {
					final R_default_Value $tmp2463 = s2361.exec_default(
							ic_in2700, senv_in3060, venv_in3150, t_in2700,
							tf_in2700, sheap_29525, vheap_28879, bool_28440,
							trace_28503);
					final IValue v2494 = $tmp2463.value;
					final ds.manual.interpreter.SState sheap_34693 = $tmp2463
							.get_1();
					final ds.manual.interpreter.VState vheap_34227 = $tmp2463
							.get_2();
					final boolean bool_33352 = $tmp2463.get_3();
					final org.spoofax.interpreter.core.StackTracer trace_33671 = $tmp2463
							.get_4();
					final ds.manual.interpreter.SState sheap_out3600 = sheap_34693;
					final ds.manual.interpreter.VState vheap_out3240 = vheap_34227;
					final boolean bool_out2700 = bool_33352;
					final org.spoofax.interpreter.core.StackTracer trace_out2880 = trace_33671;
					final IValue result_out4500 = v2494;
					return new R_default_Value(result_out4500, sheap_out3600,
							vheap_out3240, bool_out2700, trace_out2880);
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
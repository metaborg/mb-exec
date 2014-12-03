package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class mAppl_1 extends AbstractNode implements I_Matcher {
	private boolean hasSpecialized;

	@Child
	public I_STerm _1;

	public mAppl_1(INodeSource source, I_STerm _1) {
		this.setSourceInfo(source);
		this._1 = adoptChild(_1);
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
		final mAppl_1 other = (mAppl_1) obj;
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
			if (_1 instanceof IGenericNode) {
				((IGenericNode) _1).specialize(depth);
			}
			hasSpecialized = true;
		}
	}

	public R_ma_Value exec_ma(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _3,
			org.spoofax.interpreter.terms.ITermFactory _4,
			org.spoofax.interpreter.terms.IStrategoTerm _5,
			ds.manual.interpreter.SState _6, ds.manual.interpreter.VState _7,
			boolean _8, org.spoofax.interpreter.core.StackTracer _9) {
		this.specializeChildren(0);
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2718 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3078 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3168 = _3;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2718 = _4;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2718 = _5;
		final ds.manual.interpreter.SState sheap_in3618 = _6;
		final ds.manual.interpreter.VState vheap_in3258 = _7;
		final boolean bool_in2718 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2898 = _9;
		final I_STerm lifted_32237 = this._1;
		{
			final Op_2 $tmp2585 = lifted_32237.match(Op_2.class);
			if ($tmp2585 != null) {
				final String c1356 = $tmp2585.get_1();
				final INodeList<I_STerm> pts370 = $tmp2585.get_2();
				if (t_in2718 instanceof org.spoofax.interpreter.terms.IStrategoAppl) {
					final org.spoofax.interpreter.terms.IStrategoAppl t98047 = (org.spoofax.interpreter.terms.IStrategoAppl) t_in2718;
					final org.spoofax.interpreter.terms.IStrategoConstructor aconstr160 = t98047
							.getConstructor();
					final String c_10 = aconstr160.getName();
					if (c1356 != null && c1356.equals(c_10)) {
						final org.spoofax.interpreter.terms.IStrategoTerm[] subterms160 = t98047
								.getAllSubterms();
						final org.spoofax.interpreter.terms.IStrategoList asubterms160 = tf_in2718
								.makeList(subterms160);
						final I_Matcher lifted_32257 = new ml_1(null, pts370);
						final R_ma_Value $tmp2586 = lifted_32257.exec_ma(
								ic_in2718, senv_in3078, venv_in3168, tf_in2718,
								asubterms160, sheap_in3618, vheap_in3258,
								bool_in2718, trace_in2898);
						final IValue lifted_32267 = $tmp2586.value;
						final ds.manual.interpreter.SState sheap_29562 = $tmp2586
								.get_1();
						final ds.manual.interpreter.VState vheap_28916 = $tmp2586
								.get_2();
						final boolean bool_28477 = $tmp2586.get_3();
						final org.spoofax.interpreter.core.StackTracer trace_28540 = $tmp2586
								.get_4();
						final S_1 $tmp2587 = lifted_32267.match(S_1.class);
						if ($tmp2587 != null) {
							final org.spoofax.interpreter.terms.IStrategoTerm lifted_30197 = $tmp2587
									.get_1();
							final IValue lifted_32247 = new S_1(null, t98047);
							final ds.manual.interpreter.SState sheap_out3618 = sheap_29562;
							final ds.manual.interpreter.VState vheap_out3258 = vheap_28916;
							final boolean bool_out2718 = bool_28477;
							final org.spoofax.interpreter.core.StackTracer trace_out2898 = trace_28540;
							final IValue result_out4518 = lifted_32247;
							return new R_ma_Value(result_out4518,
									sheap_out3618, vheap_out3258, bool_out2718,
									trace_out2898);
						} else {
						}
					} else {
					}
				} else {
				}
			} else {
			}
		}
		{
			final IValue lifted_32277 = new F_0(null);
			final ds.manual.interpreter.SState sheap_out3618 = sheap_in3618;
			final ds.manual.interpreter.VState vheap_out3258 = vheap_in3258;
			final boolean bool_out2718 = bool_in2718;
			final org.spoofax.interpreter.core.StackTracer trace_out2898 = trace_in2898;
			final IValue result_out4518 = lifted_32277;
			return new R_ma_Value(result_out4518, sheap_out3618, vheap_out3258,
					bool_out2718, trace_out2898);
		}
	}

	public I_STerm get_1() {
		return this._1;
	}
}
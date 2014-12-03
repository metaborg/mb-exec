package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class map_1 extends AbstractNode implements I_Mapper {
	private boolean hasSpecialized;

	@Child
	public I_Strategy _1;

	public map_1(INodeSource source, I_Strategy _1) {
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
		final map_1 other = (map_1) obj;
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

	public R_map_Value exec_map(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _3,
			org.spoofax.interpreter.terms.ITermFactory _4,
			org.spoofax.interpreter.terms.IStrategoTerm _5,
			ds.manual.interpreter.SState _6, ds.manual.interpreter.VState _7,
			boolean _8, org.spoofax.interpreter.core.StackTracer _9) {
		this.specializeChildren(0);
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2724 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3084 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3174 = _3;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2724 = _4;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2724 = _5;
		final ds.manual.interpreter.SState sheap_in3624 = _6;
		final ds.manual.interpreter.VState vheap_in3264 = _7;
		final boolean bool_in2724 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2904 = _9;
		final I_Strategy s11437 = this._1;
		{
			if (t_in2724 instanceof org.spoofax.interpreter.terms.IStrategoList) {
				final org.spoofax.interpreter.terms.IStrategoList t98067 = (org.spoofax.interpreter.terms.IStrategoList) t_in2724;
				if (t98067.isEmpty() == true) {
					final IValue lifted_32287 = new S_1(null, t98067);
					final ds.manual.interpreter.SState sheap_out3624 = sheap_in3624;
					final ds.manual.interpreter.VState vheap_out3264 = vheap_in3264;
					final boolean bool_out2724 = bool_in2724;
					final org.spoofax.interpreter.core.StackTracer trace_out2904 = trace_in2904;
					final IValue result_out4524 = lifted_32287;
					return new R_map_Value(result_out4524, sheap_out3624,
							vheap_out3264, bool_out2724, trace_out2904);
				} else {
					if (t98067.isEmpty() == false) {
						final org.spoofax.interpreter.terms.IStrategoTerm th1089 = t98067
								.head();
						final org.spoofax.interpreter.terms.IStrategoList ts5869 = t98067
								.tail();
						final R_default_Value $tmp2625 = s11437.exec_default(
								ic_in2724, senv_in3084, venv_in3174, th1089,
								tf_in2724, sheap_in3624, vheap_in3264,
								bool_in2724, trace_in2904);
						final IValue lifted_32307 = $tmp2625.value;
						final ds.manual.interpreter.SState sheap_29574 = $tmp2625
								.get_1();
						final ds.manual.interpreter.VState vheap_28929 = $tmp2625
								.get_2();
						final boolean bool_28489 = $tmp2625.get_3();
						final org.spoofax.interpreter.core.StackTracer trace_28552 = $tmp2625
								.get_4();
						final S_1 $tmp2626 = lifted_32307.match(S_1.class);
						if ($tmp2626 != null) {
							final org.spoofax.interpreter.terms.IStrategoTerm th_20 = $tmp2626
									.get_1();
							final I_Mapper lifted_32317 = new map_1(null,
									s11437);
							final R_map_Value $tmp2627 = lifted_32317.exec_map(
									ic_in2724, senv_in3084, venv_in3174,
									tf_in2724, ts5869, sheap_29574,
									vheap_28929, bool_28489, trace_28552);
							final IValue lifted_32327 = $tmp2627.value;
							final ds.manual.interpreter.SState sheap_34713 = $tmp2627
									.get_1();
							final ds.manual.interpreter.VState vheap_34250 = $tmp2627
									.get_2();
							final boolean bool_33371 = $tmp2627.get_3();
							final org.spoofax.interpreter.core.StackTracer trace_33690 = $tmp2627
									.get_4();
							final S_1 $tmp2628 = lifted_32327.match(S_1.class);
							if ($tmp2628 != null) {
								final org.spoofax.interpreter.terms.IStrategoTerm ts_105 = $tmp2628
										.get_1();
								if (ts_105 instanceof org.spoofax.interpreter.terms.IStrategoList) {
									final org.spoofax.interpreter.terms.IStrategoList ts_27300 = (org.spoofax.interpreter.terms.IStrategoList) ts_105;
									final org.spoofax.interpreter.terms.IStrategoList ts__32 = tf_in2724
											.makeListCons(th_20, ts_27300);
									final IValue lifted_32297 = new S_1(null,
											ts__32);
									final ds.manual.interpreter.SState sheap_out3624 = sheap_34713;
									final ds.manual.interpreter.VState vheap_out3264 = vheap_34250;
									final boolean bool_out2724 = bool_33371;
									final org.spoofax.interpreter.core.StackTracer trace_out2904 = trace_33690;
									final IValue result_out4524 = lifted_32297;
									return new R_map_Value(result_out4524,
											sheap_out3624, vheap_out3264,
											bool_out2724, trace_out2904);
								} else {
								}
							} else {
							}
						} else {
						}
					} else {
					}
				}
			} else {
			}
		}
		{
			final IValue lifted_32337 = new F_0(null);
			final ds.manual.interpreter.SState sheap_out3624 = sheap_in3624;
			final ds.manual.interpreter.VState vheap_out3264 = vheap_in3264;
			final boolean bool_out2724 = bool_in2724;
			final org.spoofax.interpreter.core.StackTracer trace_out2904 = trace_in2904;
			final IValue result_out4524 = lifted_32337;
			return new R_map_Value(result_out4524, sheap_out3624,
					vheap_out3264, bool_out2724, trace_out2904);
		}
	}

	public I_Strategy get_1() {
		return this._1;
	}
}
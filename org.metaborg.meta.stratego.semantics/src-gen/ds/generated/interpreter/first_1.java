package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class first_1 extends AbstractNode implements I_Firster {
	private boolean hasSpecialized;

	@Child
	public I_Strategy _1;

	public first_1(INodeSource source, I_Strategy _1) {
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
		final first_1 other = (first_1) obj;
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

	public R_first_Value exec_first(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _3,
			org.spoofax.interpreter.terms.ITermFactory _4,
			org.spoofax.interpreter.terms.IStrategoTerm _5,
			ds.manual.interpreter.SState _6, ds.manual.interpreter.VState _7,
			boolean _8, org.spoofax.interpreter.core.StackTracer _9) {
		this.specializeChildren(0);
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2725 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3085 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3175 = _3;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2725 = _4;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2725 = _5;
		final ds.manual.interpreter.SState sheap_in3625 = _6;
		final ds.manual.interpreter.VState vheap_in3265 = _7;
		final boolean bool_in2725 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2905 = _9;
		final I_Strategy s11438 = this._1;
		{
			if (t_in2725 instanceof org.spoofax.interpreter.terms.IStrategoList) {
				final org.spoofax.interpreter.terms.IStrategoList t98187 = (org.spoofax.interpreter.terms.IStrategoList) t_in2725;
				if (t98187.isEmpty() == true) {
					final IValue lifted_33517 = new F_0(null);
					final ds.manual.interpreter.SState sheap_out3625 = sheap_in3625;
					final ds.manual.interpreter.VState vheap_out3265 = vheap_in3265;
					final boolean bool_out2725 = bool_in2725;
					final org.spoofax.interpreter.core.StackTracer trace_out2905 = trace_in2905;
					final IValue result_out4525 = lifted_33517;
					return new R_first_Value(result_out4525, sheap_out3625,
							vheap_out3265, bool_out2725, trace_out2905);
				} else {
					if (t98187.isEmpty() == false) {
						final org.spoofax.interpreter.terms.IStrategoTerm th1090 = t98187
								.head();
						final org.spoofax.interpreter.terms.IStrategoList tl696 = t98187
								.tail();
						final R_default_Value $tmp2629 = s11438.exec_default(
								ic_in2725, senv_in3085, venv_in3175, th1090,
								tf_in2725, sheap_in3625, vheap_in3265,
								bool_in2725, trace_in2905);
						final IValue lifted_33557 = $tmp2629.value;
						final ds.manual.interpreter.SState sheap_29575 = $tmp2629
								.get_1();
						final ds.manual.interpreter.VState vheap_28930 = $tmp2629
								.get_2();
						final boolean bool_28490 = $tmp2629.get_3();
						final org.spoofax.interpreter.core.StackTracer trace_28553 = $tmp2629
								.get_4();
						final S_1 $tmp2630 = lifted_33557.match(S_1.class);
						if ($tmp2630 != null) {
							final org.spoofax.interpreter.terms.IStrategoTerm th_21 = $tmp2630
									.get_1();
							final org.spoofax.interpreter.terms.IStrategoList t_108 = tf_in2725
									.makeListCons(th_21, tl696);
							final IValue lifted_33527 = new S_1(null, t_108);
							final ds.manual.interpreter.SState sheap_out3625 = sheap_29575;
							final ds.manual.interpreter.VState vheap_out3265 = vheap_28930;
							final boolean bool_out2725 = bool_28490;
							final org.spoofax.interpreter.core.StackTracer trace_out2905 = trace_28553;
							final IValue result_out4525 = lifted_33527;
							return new R_first_Value(result_out4525,
									sheap_out3625, vheap_out3265, bool_out2725,
									trace_out2905);
						} else {
							final F_0 $tmp2631 = lifted_33557.match(F_0.class);
							if ($tmp2631 != null) {
								final I_Firster lifted_33567 = new first_1(
										null, s11438);
								final R_first_Value $tmp2632 = lifted_33567
										.exec_first(ic_in2725, senv_in3085,
												venv_in3175, tf_in2725, tl696,
												sheap_29575, vheap_28930,
												bool_28490, trace_28553);
								final IValue lifted_33577 = $tmp2632.value;
								final ds.manual.interpreter.SState sheap_34714 = $tmp2632
										.get_1();
								final ds.manual.interpreter.VState vheap_34251 = $tmp2632
										.get_2();
								final boolean bool_33372 = $tmp2632.get_3();
								final org.spoofax.interpreter.core.StackTracer trace_33691 = $tmp2632
										.get_4();
								final S_1 $tmp2633 = lifted_33577
										.match(S_1.class);
								if ($tmp2633 != null) {
									final org.spoofax.interpreter.terms.IStrategoTerm tl_10 = $tmp2633
											.get_1();
									if (tl_10 instanceof org.spoofax.interpreter.terms.IStrategoList) {
										final org.spoofax.interpreter.terms.IStrategoList tl_3770 = (org.spoofax.interpreter.terms.IStrategoList) tl_10;
										final org.spoofax.interpreter.terms.IStrategoList t_109 = tf_in2725
												.makeListCons(th1090, tl_3770);
										final IValue lifted_33547 = new S_1(
												null, t_109);
										final ds.manual.interpreter.SState sheap_out3625 = sheap_34714;
										final ds.manual.interpreter.VState vheap_out3265 = vheap_34251;
										final boolean bool_out2725 = bool_33372;
										final org.spoofax.interpreter.core.StackTracer trace_out2905 = trace_33691;
										final IValue result_out4525 = lifted_33547;
										return new R_first_Value(
												result_out4525, sheap_out3625,
												vheap_out3265, bool_out2725,
												trace_out2905);
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
			} else {
			}
		}
		{
			final IValue lifted_33587 = new F_0(null);
			final ds.manual.interpreter.SState sheap_out3625 = sheap_in3625;
			final ds.manual.interpreter.VState vheap_out3265 = vheap_in3265;
			final boolean bool_out2725 = bool_in2725;
			final org.spoofax.interpreter.core.StackTracer trace_out2905 = trace_in2905;
			final IValue result_out4525 = lifted_33587;
			return new R_first_Value(result_out4525, sheap_out3625,
					vheap_out3265, bool_out2725, trace_out2905);
		}
	}

	public I_Strategy get_1() {
		return this._1;
	}
}
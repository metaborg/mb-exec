package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class CallT_3 extends AbstractNode implements I_Strategy {
	private boolean hasSpecialized;

	@Child
	public I_SVar _1;

	@Children
	public INodeList<I_Strategy> _2;

	@Children
	public INodeList<I_STerm> _3;

	public CallT_3(INodeSource source, I_SVar _1, INodeList<I_Strategy> _2,
			INodeList<I_STerm> _3) {
		this.setSourceInfo(source);
		this._1 = adoptChild(_1);
		this._2 = adoptChildren(_2);
		this._3 = adoptChildren(_3);
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
		final CallT_3 other = (CallT_3) obj;
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
			for (I_Strategy _2_elem : _2) {
				if (_2_elem instanceof IGenericNode) {
					((IGenericNode) _2_elem).specialize(depth);
				}
			}
			for (I_STerm _3_elem : _3) {
				if (_3_elem instanceof IGenericNode) {
					((IGenericNode) _3_elem).specialize(depth);
				}
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2713 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3073 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3163 = _3;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2713 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2713 = _5;
		final ds.manual.interpreter.SState sheap_in3613 = _6;
		final ds.manual.interpreter.VState vheap_in3253 = _7;
		final boolean bool_in2713 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2893 = _9;
		final I_SVar lifted_33607 = this._1;
		final INodeList<I_Strategy> ass1007 = this._2;
		final INodeList<I_STerm> ats3827 = this._3;
		{
			final SVar_1 $tmp2518 = lifted_33607.match(SVar_1.class);
			if ($tmp2518 != null) {
				final String sname1209 = $tmp2518.get_1();
				final I_SHeapOp lifted_33617 = new SLookup_2(null, senv_in3073,
						sname1209);
				final R_slook_SLookupResult $tmp2519 = lifted_33617
						.exec_slook(sheap_in3613);
				final I_SLookupResult lifted_33627 = $tmp2519.value;
				final ds.manual.interpreter.SState sheap_29546 = $tmp2519
						.get_1();
				final SLookupResult_2 $tmp2520 = lifted_33627
						.match(SLookupResult_2.class);
				if ($tmp2520 != null) {
					final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> lifted_30527 = $tmp2520
							.get_1();
					final I_Thunk thunk1737 = $tmp2520.get_2();
					final Thunk_6 $tmp2521 = thunk1737.match(Thunk_6.class);
					if ($tmp2521 != null) {
						final String lifted_30537 = $tmp2521.get_1();
						final INodeList<String> fss567 = $tmp2521.get_2();
						final INodeList<String> fts567 = $tmp2521.get_3();
						final I_Strategy s11432 = $tmp2521.get_4();
						final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> e_def189 = $tmp2521
								.get_5();
						final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> d_def189 = $tmp2521
								.get_6();
						final I_Builder lifted_33637 = new bl_1(null, ats3827);
						final R_blds_BuildRes $tmp2522 = lifted_33637
								.exec_blds(ic_in2713, senv_in3073, venv_in3163,
										t_in2713, tf_in2713, sheap_29546,
										vheap_in3253, bool_in2713, trace_in2893);
						final I_BuildRes lifted_33647 = $tmp2522.value;
						final ds.manual.interpreter.SState sheap_34701 = $tmp2522
								.get_1();
						final ds.manual.interpreter.VState vheap_28900 = $tmp2522
								.get_2();
						final boolean bool_28461 = $tmp2522.get_3();
						final org.spoofax.interpreter.core.StackTracer trace_28524 = $tmp2522
								.get_4();
						final BSS_1 $tmp2523 = lifted_33647.match(BSS_1.class);
						if ($tmp2523 != null) {
							final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> ats_119 = $tmp2523
									.get_1();
							final I_Binder lifted_33657 = new bindtvars_2(null,
									fts567, ats_119);
							final R_bindtvars_VEnv $tmp2524 = lifted_33657
									.exec_bindtvars(e_def189, vheap_28900);
							final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> e_call189 = $tmp2524.value;
							final ds.manual.interpreter.VState vheap_34236 = $tmp2524
									.get_1();
							final I_Binder lifted_33667 = new bindsvars_3(null,
									fss567, ass1007, d_def189);
							final R_bindsvars_SEnv $tmp2525 = lifted_33667
									.exec_bindsvars(ic_in2713, t_in2713,
											tf_in2713, venv_in3163,
											senv_in3073, bool_28461,
											trace_28524, vheap_34236,
											sheap_34701);
							final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> d_call189 = $tmp2525.value;
							final boolean bool_33360 = $tmp2525.get_1();
							final org.spoofax.interpreter.core.StackTracer trace_33679 = $tmp2525
									.get_2();
							final ds.manual.interpreter.VState vheap_4417 = $tmp2525
									.get_3();
							final ds.manual.interpreter.SState sheap_4557 = $tmp2525
									.get_4();
							final I_TraceOp lifted_33677 = new tracing_2(null,
									sname1209, s11432);
							final R_default_Value $tmp2526 = lifted_33677
									.exec_default(ic_in2713, d_call189,
											e_call189, t_in2713, tf_in2713,
											sheap_4557, vheap_4417, bool_33360,
											trace_33679);
							final IValue v4122 = $tmp2526.value;
							final ds.manual.interpreter.SState sheap_5278 = $tmp2526
									.get_1();
							final ds.manual.interpreter.VState vheap_5278 = $tmp2526
									.get_2();
							final boolean bool_4278 = $tmp2526.get_3();
							final org.spoofax.interpreter.core.StackTracer trace_4417 = $tmp2526
									.get_4();
							final ds.manual.interpreter.SState sheap_out3613 = sheap_5278;
							final ds.manual.interpreter.VState vheap_out3253 = vheap_5278;
							final boolean bool_out2713 = bool_4278;
							final org.spoofax.interpreter.core.StackTracer trace_out2893 = trace_4417;
							final IValue result_out4513 = v4122;
							return new R_default_Value(result_out4513,
									sheap_out3613, vheap_out3253, bool_out2713,
									trace_out2893);
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
			final IValue lifted_33687 = new F_0(null);
			final ds.manual.interpreter.SState sheap_out3613 = sheap_in3613;
			final ds.manual.interpreter.VState vheap_out3253 = vheap_in3253;
			final boolean bool_out2713 = bool_in2713;
			final org.spoofax.interpreter.core.StackTracer trace_out2893 = trace_in2893;
			final IValue result_out4513 = lifted_33687;
			return new R_default_Value(result_out4513, sheap_out3613,
					vheap_out3253, bool_out2713, trace_out2893);
		}
	}

	public I_SVar get_1() {
		return this._1;
	}

	public INodeList<I_Strategy> get_2() {
		return this._2;
	}

	public INodeList<I_STerm> get_3() {
		return this._3;
	}
}
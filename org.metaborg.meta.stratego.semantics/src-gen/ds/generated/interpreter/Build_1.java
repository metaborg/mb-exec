package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Build_1 extends AbstractNode implements I_Strategy {
	private boolean hasSpecialized;

	@Child
	public I_STerm _1;

	public Build_1(INodeSource source, I_STerm _1) {
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
		final Build_1 other = (Build_1) obj;
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

	public R_default_Value exec_default(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _3,
			org.spoofax.interpreter.terms.IStrategoTerm _4,
			org.spoofax.interpreter.terms.ITermFactory _5,
			ds.manual.interpreter.SState _6, ds.manual.interpreter.VState _7,
			boolean _8, org.spoofax.interpreter.core.StackTracer _9) {
		this.specializeChildren(0);
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2708 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3068 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3158 = _3;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2708 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2708 = _5;
		final ds.manual.interpreter.SState sheap_in3608 = _6;
		final ds.manual.interpreter.VState vheap_in3248 = _7;
		final boolean bool_in2708 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2888 = _9;
		final I_STerm lifted_33797 = this._1;
		{
			final Anno_2 $tmp2494 = lifted_33797.match(Anno_2.class);
			if ($tmp2494 != null) {
				final I_PreTerm t10084 = $tmp2494.get_1();
				final I_PreTerm annos236 = $tmp2494.get_2();
				final I_Builder lifted_33757 = new b_1(null, t10084);
				final R_bld_BuildRes $tmp2495 = lifted_33757.exec_bld(
						ic_in2708, senv_in3068, t_in2708, venv_in3158,
						tf_in2708, sheap_in3608, vheap_in3248, bool_in2708,
						trace_in2888);
				final I_BuildRes lifted_33767 = $tmp2495.value;
				final ds.manual.interpreter.SState sheap_29539 = $tmp2495
						.get_1();
				final ds.manual.interpreter.VState vheap_28893 = $tmp2495
						.get_2();
				final boolean bool_28454 = $tmp2495.get_3();
				final org.spoofax.interpreter.core.StackTracer trace_28517 = $tmp2495
						.get_4();
				final BS_1 $tmp2496 = lifted_33767.match(BS_1.class);
				if ($tmp2496 != null) {
					final org.spoofax.interpreter.terms.IStrategoTerm at216 = $tmp2496
							.get_1();
					final I_Builder lifted_33777 = new b_1(null, annos236);
					final R_bld_BuildRes $tmp2497 = lifted_33777.exec_bld(
							ic_in2708, senv_in3068, t_in2708, venv_in3158,
							tf_in2708, sheap_29539, vheap_28893, bool_28454,
							trace_28517);
					final I_BuildRes lifted_33787 = $tmp2497.value;
					final ds.manual.interpreter.SState sheap_34698 = $tmp2497
							.get_1();
					final ds.manual.interpreter.VState vheap_34233 = $tmp2497
							.get_2();
					final boolean bool_33357 = $tmp2497.get_3();
					final org.spoofax.interpreter.core.StackTracer trace_33676 = $tmp2497
							.get_4();
					final BS_1 $tmp2498 = lifted_33787.match(BS_1.class);
					if ($tmp2498 != null) {
						final org.spoofax.interpreter.terms.IStrategoTerm annos_aterm201 = $tmp2498
								.get_1();
						if (annos_aterm201 instanceof org.spoofax.interpreter.terms.IStrategoList) {
							final org.spoofax.interpreter.terms.IStrategoList annos_aterm1887 = (org.spoofax.interpreter.terms.IStrategoList) annos_aterm201;
							final org.spoofax.interpreter.terms.IStrategoTerm aterm195 = tf_in2708
									.annotateTerm(at216, annos_aterm1887);
							final IValue lifted_33747 = new S_1(null, aterm195);
							final ds.manual.interpreter.SState sheap_out3608 = sheap_34698;
							final ds.manual.interpreter.VState vheap_out3248 = vheap_34233;
							final boolean bool_out2708 = bool_33357;
							final org.spoofax.interpreter.core.StackTracer trace_out2888 = trace_33676;
							final IValue result_out4508 = lifted_33747;
							return new R_default_Value(result_out4508,
									sheap_out3608, vheap_out3248, bool_out2708,
									trace_out2888);
						} else {
						}
					} else {
					}
				} else {
				}
			} else {
				final Var_1 $tmp2499 = lifted_33797.match(Var_1.class);
				if ($tmp2499 != null) {
					final String x3747 = $tmp2499.get_1();
					final I_Var lifted_33837 = new Var_1(null, x3747);
					final I_Builder lifted_33817 = new b_1(null, lifted_33837);
					final R_bld_BuildRes $tmp2500 = lifted_33817.exec_bld(
							ic_in2708, senv_in3068, t_in2708, venv_in3158,
							tf_in2708, sheap_in3608, vheap_in3248, bool_in2708,
							trace_in2888);
					final I_BuildRes lifted_33827 = $tmp2500.value;
					final ds.manual.interpreter.SState sheap_29540 = $tmp2500
							.get_1();
					final ds.manual.interpreter.VState vheap_28894 = $tmp2500
							.get_2();
					final boolean bool_28455 = $tmp2500.get_3();
					final org.spoofax.interpreter.core.StackTracer trace_28518 = $tmp2500
							.get_4();
					final BS_1 $tmp2501 = lifted_33827.match(BS_1.class);
					if ($tmp2501 != null) {
						final org.spoofax.interpreter.terms.IStrategoTerm t10085 = $tmp2501
								.get_1();
						final IValue lifted_33807 = new S_1(null, t10085);
						final ds.manual.interpreter.SState sheap_out3608 = sheap_29540;
						final ds.manual.interpreter.VState vheap_out3248 = vheap_28894;
						final boolean bool_out2708 = bool_28455;
						final org.spoofax.interpreter.core.StackTracer trace_out2888 = trace_28518;
						final IValue result_out4508 = lifted_33807;
						return new R_default_Value(result_out4508,
								sheap_out3608, vheap_out3248, bool_out2708,
								trace_out2888);
					} else {
					}
				} else {
				}
			}
		}
		{
			final IValue lifted_33847 = new F_0(null);
			final ds.manual.interpreter.SState sheap_out3608 = sheap_in3608;
			final ds.manual.interpreter.VState vheap_out3248 = vheap_in3248;
			final boolean bool_out2708 = bool_in2708;
			final org.spoofax.interpreter.core.StackTracer trace_out2888 = trace_in2888;
			final IValue result_out4508 = lifted_33847;
			return new R_default_Value(result_out4508, sheap_out3608,
					vheap_out3248, bool_out2708, trace_out2888);
		}
	}

	public I_STerm get_1() {
		return this._1;
	}
}
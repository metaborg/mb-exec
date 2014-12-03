package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Match_1 extends AbstractNode implements I_Strategy {
	private boolean hasSpecialized;

	@Child
	public I_STerm _1;

	public Match_1(INodeSource source, I_STerm _1) {
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
		final Match_1 other = (Match_1) obj;
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2709 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3069 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3159 = _3;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2709 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2709 = _5;
		final ds.manual.interpreter.SState sheap_in3609 = _6;
		final ds.manual.interpreter.VState vheap_in3249 = _7;
		final boolean bool_in2709 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2889 = _9;
		final I_STerm lifted_32197 = this._1;
		{
			final Wld_0 $tmp2502 = lifted_32197.match(Wld_0.class);
			if ($tmp2502 != null) {
				final IValue lifted_32057 = new S_1(null, t_in2709);
				final ds.manual.interpreter.SState sheap_out3609 = sheap_in3609;
				final ds.manual.interpreter.VState vheap_out3249 = vheap_in3249;
				final boolean bool_out2709 = bool_in2709;
				final org.spoofax.interpreter.core.StackTracer trace_out2889 = trace_in2889;
				final IValue result_out4509 = lifted_32057;
				return new R_default_Value(result_out4509, sheap_out3609,
						vheap_out3249, bool_out2709, trace_out2889);
			} else {
				final NoAnnoList_1 $tmp2503 = lifted_32197
						.match(NoAnnoList_1.class);
				if ($tmp2503 != null) {
					final I_PreTerm mp320 = $tmp2503.get_1();
					final I_Matcher lifted_32077 = new m_1(null, mp320);
					final R_ma_Value $tmp2504 = lifted_32077.exec_ma(ic_in2709,
							senv_in3069, venv_in3159, tf_in2709, t_in2709,
							sheap_in3609, vheap_in3249, bool_in2709,
							trace_in2889);
					final IValue v4119 = $tmp2504.value;
					final ds.manual.interpreter.SState sheap_29541 = $tmp2504
							.get_1();
					final ds.manual.interpreter.VState vheap_28895 = $tmp2504
							.get_2();
					final boolean bool_28456 = $tmp2504.get_3();
					final org.spoofax.interpreter.core.StackTracer trace_28519 = $tmp2504
							.get_4();
					final ds.manual.interpreter.SState sheap_out3609 = sheap_29541;
					final ds.manual.interpreter.VState vheap_out3249 = vheap_28895;
					final boolean bool_out2709 = bool_28456;
					final org.spoofax.interpreter.core.StackTracer trace_out2889 = trace_28519;
					final IValue result_out4509 = v4119;
					return new R_default_Value(result_out4509, sheap_out3609,
							vheap_out3249, bool_out2709, trace_out2889);
				} else {
					final Anno_2 $tmp2505 = lifted_32197.match(Anno_2.class);
					if ($tmp2505 != null) {
						final I_PreTerm mp321 = $tmp2505.get_1();
						final I_PreTerm ma160 = $tmp2505.get_2();
						final I_Matcher lifted_32107 = new m_1(null, mp321);
						final R_ma_Value $tmp2506 = lifted_32107.exec_ma(
								ic_in2709, senv_in3069, venv_in3159, tf_in2709,
								t_in2709, sheap_in3609, vheap_in3249,
								bool_in2709, trace_in2889);
						final IValue lifted_32117 = $tmp2506.value;
						final ds.manual.interpreter.SState sheap_29542 = $tmp2506
								.get_1();
						final ds.manual.interpreter.VState vheap_28896 = $tmp2506
								.get_2();
						final boolean bool_28457 = $tmp2506.get_3();
						final org.spoofax.interpreter.core.StackTracer trace_28520 = $tmp2506
								.get_4();
						final S_1 $tmp2507 = lifted_32117.match(S_1.class);
						if ($tmp2507 != null) {
							final org.spoofax.interpreter.terms.IStrategoTerm lifted_30157 = $tmp2507
									.get_1();
							final org.spoofax.interpreter.terms.IStrategoList aa160 = t_in2709
									.getAnnotations();
							final I_Matcher lifted_32127 = new m_1(null, ma160);
							final R_ma_Value $tmp2508 = lifted_32127.exec_ma(
									ic_in2709, senv_in3069, venv_in3159,
									tf_in2709, aa160, sheap_29542, vheap_28896,
									bool_28457, trace_28520);
							final IValue lifted_32137 = $tmp2508.value;
							final ds.manual.interpreter.SState sheap_34699 = $tmp2508
									.get_1();
							final ds.manual.interpreter.VState vheap_34234 = $tmp2508
									.get_2();
							final boolean bool_33358 = $tmp2508.get_3();
							final org.spoofax.interpreter.core.StackTracer trace_33677 = $tmp2508
									.get_4();
							final S_1 $tmp2509 = lifted_32137.match(S_1.class);
							if ($tmp2509 != null) {
								final org.spoofax.interpreter.terms.IStrategoTerm lifted_30167 = $tmp2509
										.get_1();
								final IValue lifted_32097 = new S_1(null,
										t_in2709);
								final ds.manual.interpreter.SState sheap_out3609 = sheap_34699;
								final ds.manual.interpreter.VState vheap_out3249 = vheap_34234;
								final boolean bool_out2709 = bool_33358;
								final org.spoofax.interpreter.core.StackTracer trace_out2889 = trace_33677;
								final IValue result_out4509 = lifted_32097;
								return new R_default_Value(result_out4509,
										sheap_out3609, vheap_out3249,
										bool_out2709, trace_out2889);
							} else {
							}
						} else {
						}
					} else {
						final Var_1 $tmp2510 = lifted_32197.match(Var_1.class);
						if ($tmp2510 != null) {
							final String x3748 = $tmp2510.get_1();
							final I_Var lifted_32187 = new Var_1(null, x3748);
							final I_Matcher lifted_32167 = new m_1(null,
									lifted_32187);
							final R_ma_Value $tmp2511 = lifted_32167.exec_ma(
									ic_in2709, senv_in3069, venv_in3159,
									tf_in2709, t_in2709, sheap_in3609,
									vheap_in3249, bool_in2709, trace_in2889);
							final IValue lifted_32177 = $tmp2511.value;
							final ds.manual.interpreter.SState sheap_29543 = $tmp2511
									.get_1();
							final ds.manual.interpreter.VState vheap_28897 = $tmp2511
									.get_2();
							final boolean bool_28458 = $tmp2511.get_3();
							final org.spoofax.interpreter.core.StackTracer trace_28521 = $tmp2511
									.get_4();
							final S_1 $tmp2512 = lifted_32177.match(S_1.class);
							if ($tmp2512 != null) {
								final org.spoofax.interpreter.terms.IStrategoTerm lifted_30177 = $tmp2512
										.get_1();
								final IValue lifted_32157 = new S_1(null,
										t_in2709);
								final ds.manual.interpreter.SState sheap_out3609 = sheap_29543;
								final ds.manual.interpreter.VState vheap_out3249 = vheap_28897;
								final boolean bool_out2709 = bool_28458;
								final org.spoofax.interpreter.core.StackTracer trace_out2889 = trace_28521;
								final IValue result_out4509 = lifted_32157;
								return new R_default_Value(result_out4509,
										sheap_out3609, vheap_out3249,
										bool_out2709, trace_out2889);
							} else {
							}
						} else {
							final As_2 $tmp2513 = lifted_32197
									.match(As_2.class);
							if ($tmp2513 != null) {
								final I_Var var160 = $tmp2513.get_1();
								final I_PreTerm p758 = $tmp2513.get_2();
								final I_PreTerm lifted_32217 = new As_2(null,
										var160, p758);
								final I_Matcher lifted_32207 = new m_1(null,
										lifted_32217);
								final R_ma_Value $tmp2514 = lifted_32207
										.exec_ma(ic_in2709, senv_in3069,
												venv_in3159, tf_in2709,
												t_in2709, sheap_in3609,
												vheap_in3249, bool_in2709,
												trace_in2889);
								final IValue v4120 = $tmp2514.value;
								final ds.manual.interpreter.SState sheap_29544 = $tmp2514
										.get_1();
								final ds.manual.interpreter.VState vheap_28898 = $tmp2514
										.get_2();
								final boolean bool_28459 = $tmp2514.get_3();
								final org.spoofax.interpreter.core.StackTracer trace_28522 = $tmp2514
										.get_4();
								final ds.manual.interpreter.SState sheap_out3609 = sheap_29544;
								final ds.manual.interpreter.VState vheap_out3249 = vheap_28898;
								final boolean bool_out2709 = bool_28459;
								final org.spoofax.interpreter.core.StackTracer trace_out2889 = trace_28522;
								final IValue result_out4509 = v4120;
								return new R_default_Value(result_out4509,
										sheap_out3609, vheap_out3249,
										bool_out2709, trace_out2889);
							} else {
							}
						}
					}
				}
			}
		}
		{
			final IValue lifted_32227 = new F_0(null);
			final ds.manual.interpreter.SState sheap_out3609 = sheap_in3609;
			final ds.manual.interpreter.VState vheap_out3249 = vheap_in3249;
			final boolean bool_out2709 = bool_in2709;
			final org.spoofax.interpreter.core.StackTracer trace_out2889 = trace_in2889;
			final IValue result_out4509 = lifted_32227;
			return new R_default_Value(result_out4509, sheap_out3609,
					vheap_out3249, bool_out2709, trace_out2889);
		}
	}

	public I_STerm get_1() {
		return this._1;
	}
}
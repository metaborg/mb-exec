package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class One_1 extends AbstractNode implements I_Strategy {
	private boolean hasSpecialized;

	@Child
	public I_Strategy _1;

	public One_1(INodeSource source, I_Strategy _1) {
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
		final One_1 other = (One_1) obj;
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2702 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3062 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3152 = _3;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2702 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2702 = _5;
		final ds.manual.interpreter.SState sheap_in3602 = _6;
		final ds.manual.interpreter.VState vheap_in3242 = _7;
		final boolean bool_in2702 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2882 = _9;
		final I_Strategy s11429 = this._1;
		{
			if (t_in2702 instanceof org.spoofax.interpreter.terms.IStrategoTuple) {
				final org.spoofax.interpreter.terms.IStrategoTuple t97977 = (org.spoofax.interpreter.terms.IStrategoTuple) t_in2702;
				final org.spoofax.interpreter.terms.IStrategoTerm[] ts5859 = t97977
						.getAllSubterms();
				final org.spoofax.interpreter.terms.IStrategoList ats3820 = tf_in2702
						.makeList(ts5859);
				final I_Firster lifted_31517 = new first_1(null, s11429);
				final R_first_Value $tmp2470 = lifted_31517.exec_first(
						ic_in2702, senv_in3062, venv_in3152, tf_in2702,
						ats3820, sheap_in3602, vheap_in3242, bool_in2702,
						trace_in2882);
				final IValue lifted_31527 = $tmp2470.value;
				final ds.manual.interpreter.SState sheap_29529 = $tmp2470
						.get_1();
				final ds.manual.interpreter.VState vheap_28883 = $tmp2470
						.get_2();
				final boolean bool_28444 = $tmp2470.get_3();
				final org.spoofax.interpreter.core.StackTracer trace_28507 = $tmp2470
						.get_4();
				final S_1 $tmp2471 = lifted_31527.match(S_1.class);
				if ($tmp2471 != null) {
					final org.spoofax.interpreter.terms.IStrategoTerm t_102 = $tmp2471
							.get_1();
					final org.spoofax.interpreter.terms.IStrategoList lifted_31537 = t97977
							.getAnnotations();
					final org.spoofax.interpreter.terms.IStrategoTerm t__57 = tf_in2702
							.annotateTerm(t_102, lifted_31537);
					final IValue lifted_31507 = new S_1(null, t__57);
					final ds.manual.interpreter.SState sheap_out3602 = sheap_29529;
					final ds.manual.interpreter.VState vheap_out3242 = vheap_28883;
					final boolean bool_out2702 = bool_28444;
					final org.spoofax.interpreter.core.StackTracer trace_out2882 = trace_28507;
					final IValue result_out4502 = lifted_31507;
					return new R_default_Value(result_out4502, sheap_out3602,
							vheap_out3242, bool_out2702, trace_out2882);
				} else {
				}
			} else {
				if (t_in2702 instanceof org.spoofax.interpreter.terms.IStrategoList) {
					final org.spoofax.interpreter.terms.IStrategoList t97987 = (org.spoofax.interpreter.terms.IStrategoList) t_in2702;
					final org.spoofax.interpreter.terms.IStrategoTerm[] ts5860 = t97987
							.getAllSubterms();
					final org.spoofax.interpreter.terms.IStrategoList ats3821 = tf_in2702
							.makeList(ts5860);
					final I_Firster lifted_31557 = new first_1(null, s11429);
					final R_first_Value $tmp2472 = lifted_31557.exec_first(
							ic_in2702, senv_in3062, venv_in3152, tf_in2702,
							ats3821, sheap_in3602, vheap_in3242, bool_in2702,
							trace_in2882);
					final IValue lifted_31567 = $tmp2472.value;
					final ds.manual.interpreter.SState sheap_29530 = $tmp2472
							.get_1();
					final ds.manual.interpreter.VState vheap_28884 = $tmp2472
							.get_2();
					final boolean bool_28445 = $tmp2472.get_3();
					final org.spoofax.interpreter.core.StackTracer trace_28508 = $tmp2472
							.get_4();
					final S_1 $tmp2473 = lifted_31567.match(S_1.class);
					if ($tmp2473 != null) {
						final org.spoofax.interpreter.terms.IStrategoTerm ats_113 = $tmp2473
								.get_1();
						final org.spoofax.interpreter.terms.IStrategoList lifted_31577 = t97987
								.getAnnotations();
						final org.spoofax.interpreter.terms.IStrategoTerm ats__26 = tf_in2702
								.annotateTerm(ats_113, lifted_31577);
						final IValue lifted_31547 = new S_1(null, ats__26);
						final ds.manual.interpreter.SState sheap_out3602 = sheap_29530;
						final ds.manual.interpreter.VState vheap_out3242 = vheap_28884;
						final boolean bool_out2702 = bool_28445;
						final org.spoofax.interpreter.core.StackTracer trace_out2882 = trace_28508;
						final IValue result_out4502 = lifted_31547;
						return new R_default_Value(result_out4502,
								sheap_out3602, vheap_out3242, bool_out2702,
								trace_out2882);
					} else {
					}
				} else {
					if (t_in2702 instanceof org.spoofax.interpreter.terms.IStrategoAppl) {
						final org.spoofax.interpreter.terms.IStrategoAppl t97997 = (org.spoofax.interpreter.terms.IStrategoAppl) t_in2702;
						final org.spoofax.interpreter.terms.IStrategoConstructor ac1180 = t97997
								.getConstructor();
						final org.spoofax.interpreter.terms.IStrategoTerm[] ts5861 = t97997
								.getAllSubterms();
						final org.spoofax.interpreter.terms.IStrategoList ats3822 = tf_in2702
								.makeList(ts5861);
						final I_Firster lifted_31597 = new first_1(null, s11429);
						final R_first_Value $tmp2474 = lifted_31597.exec_first(
								ic_in2702, senv_in3062, venv_in3152, tf_in2702,
								ats3822, sheap_in3602, vheap_in3242,
								bool_in2702, trace_in2882);
						final IValue lifted_31607 = $tmp2474.value;
						final ds.manual.interpreter.SState sheap_29531 = $tmp2474
								.get_1();
						final ds.manual.interpreter.VState vheap_28885 = $tmp2474
								.get_2();
						final boolean bool_28446 = $tmp2474.get_3();
						final org.spoofax.interpreter.core.StackTracer trace_28509 = $tmp2474
								.get_4();
						final S_1 $tmp2475 = lifted_31607.match(S_1.class);
						if ($tmp2475 != null) {
							final org.spoofax.interpreter.terms.IStrategoTerm ats_114 = $tmp2475
									.get_1();
							if (ats_114 instanceof org.spoofax.interpreter.terms.IStrategoList) {
								final org.spoofax.interpreter.terms.IStrategoList ats_34870 = (org.spoofax.interpreter.terms.IStrategoList) ats_114;
								final org.spoofax.interpreter.terms.IStrategoTerm[] ts_101 = ats_34870
										.getAllSubterms();
								final org.spoofax.interpreter.terms.IStrategoAppl t_103 = tf_in2702
										.makeAppl(ac1180, ts_101);
								final org.spoofax.interpreter.terms.IStrategoList lifted_31617 = t97997
										.getAnnotations();
								final org.spoofax.interpreter.terms.IStrategoTerm t__58 = tf_in2702
										.annotateTerm(t_103, lifted_31617);
								final IValue lifted_31587 = new S_1(null, t__58);
								final ds.manual.interpreter.SState sheap_out3602 = sheap_29531;
								final ds.manual.interpreter.VState vheap_out3242 = vheap_28885;
								final boolean bool_out2702 = bool_28446;
								final org.spoofax.interpreter.core.StackTracer trace_out2882 = trace_28509;
								final IValue result_out4502 = lifted_31587;
								return new R_default_Value(result_out4502,
										sheap_out3602, vheap_out3242,
										bool_out2702, trace_out2882);
							} else {
							}
						} else {
						}
					} else {
					}
				}
			}
		}
		{
			final IValue lifted_31627 = new F_0(null);
			final ds.manual.interpreter.SState sheap_out3602 = sheap_in3602;
			final ds.manual.interpreter.VState vheap_out3242 = vheap_in3242;
			final boolean bool_out2702 = bool_in2702;
			final org.spoofax.interpreter.core.StackTracer trace_out2882 = trace_in2882;
			final IValue result_out4502 = lifted_31627;
			return new R_default_Value(result_out4502, sheap_out3602,
					vheap_out3242, bool_out2702, trace_out2882);
		}
	}

	public I_Strategy get_1() {
		return this._1;
	}
}
package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class oneOrMore_1 extends AbstractNode implements I_Somer {
	private boolean hasSpecialized;

	@Child
	public I_Strategy _1;

	public oneOrMore_1(INodeSource source, I_Strategy _1) {
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
		final oneOrMore_1 other = (oneOrMore_1) obj;
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

	public R_some_TLIST exec_some(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _3,
			org.spoofax.interpreter.terms.ITermFactory _4,
			org.spoofax.interpreter.terms.IStrategoTerm _5,
			ds.manual.interpreter.SState _6, ds.manual.interpreter.VState _7,
			org.spoofax.interpreter.core.StackTracer _8, boolean _9) {
		this.specializeChildren(0);
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2726 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3086 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3176 = _3;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2726 = _4;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2726 = _5;
		final ds.manual.interpreter.SState sheap_in3626 = _6;
		final ds.manual.interpreter.VState vheap_in3266 = _7;
		final org.spoofax.interpreter.core.StackTracer trace_in2906 = _8;
		final boolean bool_in2726 = _9;
		final I_Strategy s11439 = this._1;
		{
			if (t_in2726 instanceof org.spoofax.interpreter.terms.IStrategoList) {
				final org.spoofax.interpreter.terms.IStrategoList t97967 = (org.spoofax.interpreter.terms.IStrategoList) t_in2726;
				if (t97967.isEmpty() == true) {
					final ds.manual.interpreter.SState sheap_out3626 = sheap_in3626;
					final ds.manual.interpreter.VState vheap_out3266 = vheap_in3266;
					final org.spoofax.interpreter.core.StackTracer trace_out2906 = trace_in2906;
					final boolean bool_out2726 = false;
					final org.spoofax.interpreter.terms.IStrategoList result_out4526 = t97967;
					return new R_some_TLIST(result_out4526, sheap_out3626,
							vheap_out3266, trace_out2906, bool_out2726);
				} else {
					if (t97967.isEmpty() == false) {
						final org.spoofax.interpreter.terms.IStrategoTerm lifted_31457 = t97967
								.head();
						final R_default_Value $tmp2634 = s11439.exec_default(
								ic_in2726, senv_in3086, venv_in3176,
								lifted_31457, tf_in2726, sheap_in3626,
								vheap_in3266, bool_in2726, trace_in2906);
						final IValue lifted_31467 = $tmp2634.value;
						final ds.manual.interpreter.SState sheap_29576 = $tmp2634
								.get_1();
						final ds.manual.interpreter.VState vheap_28931 = $tmp2634
								.get_2();
						final boolean bool_28491 = $tmp2634.get_3();
						final org.spoofax.interpreter.core.StackTracer trace_28554 = $tmp2634
								.get_4();
						final F_0 $tmp2635 = lifted_31467.match(F_0.class);
						if ($tmp2635 != null) {
							final org.spoofax.interpreter.terms.IStrategoList lifted_31427 = t97967
									.tail();
							final I_Somer lifted_31437 = new oneOrMore_1(null,
									s11439);
							final R_some_TLIST $tmp2636 = lifted_31437
									.exec_some(ic_in2726, senv_in3086,
											venv_in3176, tf_in2726,
											lifted_31427, sheap_29576,
											vheap_28931, trace_28554,
											bool_in2726);
							final org.spoofax.interpreter.terms.IStrategoList tail_20 = $tmp2636.value;
							final ds.manual.interpreter.SState sheap_34715 = $tmp2636
									.get_1();
							final ds.manual.interpreter.VState vheap_34252 = $tmp2636
									.get_2();
							final org.spoofax.interpreter.core.StackTracer trace_33692 = $tmp2636
									.get_3();
							final boolean f_15 = $tmp2636.get_4();
							final org.spoofax.interpreter.terms.IStrategoList list_20 = tf_in2726
									.makeListCons(lifted_31457, tail_20);
							final boolean lifted_31407 = ds.manual.interpreter.Natives
									.boolOr_2(bool_in2726, f_15);
							final ds.manual.interpreter.SState sheap_out3626 = sheap_34715;
							final ds.manual.interpreter.VState vheap_out3266 = vheap_34252;
							final org.spoofax.interpreter.core.StackTracer trace_out2906 = trace_33692;
							final boolean bool_out2726 = lifted_31407;
							final org.spoofax.interpreter.terms.IStrategoList result_out4526 = list_20;
							return new R_some_TLIST(result_out4526,
									sheap_out3626, vheap_out3266,
									trace_out2906, bool_out2726);
						} else {
							final S_1 $tmp2637 = lifted_31467.match(S_1.class);
							if ($tmp2637 != null) {
								final org.spoofax.interpreter.terms.IStrategoTerm head_10 = $tmp2637
										.get_1();
								final org.spoofax.interpreter.terms.IStrategoList lifted_31477 = t97967
										.tail();
								final I_Somer lifted_31487 = new oneOrMore_1(
										null, s11439);
								final R_some_TLIST $tmp2638 = lifted_31487
										.exec_some(ic_in2726, senv_in3086,
												venv_in3176, tf_in2726,
												lifted_31477, sheap_29576,
												vheap_28931, trace_28554, true);
								final org.spoofax.interpreter.terms.IStrategoList tail_21 = $tmp2638.value;
								final ds.manual.interpreter.SState sheap_34716 = $tmp2638
										.get_1();
								final ds.manual.interpreter.VState vheap_34253 = $tmp2638
										.get_2();
								final org.spoofax.interpreter.core.StackTracer trace_33693 = $tmp2638
										.get_3();
								final boolean f_16 = $tmp2638.get_4();
								final org.spoofax.interpreter.terms.IStrategoList list_21 = tf_in2726
										.makeListCons(head_10, tail_21);
								final ds.manual.interpreter.SState sheap_out3626 = sheap_34716;
								final ds.manual.interpreter.VState vheap_out3266 = vheap_34253;
								final org.spoofax.interpreter.core.StackTracer trace_out2906 = trace_33693;
								final boolean bool_out2726 = true;
								final org.spoofax.interpreter.terms.IStrategoList result_out4526 = list_21;
								return new R_some_TLIST(result_out4526,
										sheap_out3626, vheap_out3266,
										trace_out2906, bool_out2726);
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
			throw new InterpreterException("Rule failure");
		}
	}

	public I_Strategy get_1() {
		return this._1;
	}
}
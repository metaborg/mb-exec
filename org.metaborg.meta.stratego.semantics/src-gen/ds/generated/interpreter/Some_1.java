package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Some_1 extends AbstractNode implements I_Strategy {
	private boolean hasSpecialized;

	@Child
	public I_Strategy _1;

	public Some_1(INodeSource source, I_Strategy _1) {
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
		final Some_1 other = (Some_1) obj;
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2703 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3063 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3153 = _3;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2703 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2703 = _5;
		final ds.manual.interpreter.SState sheap_in3603 = _6;
		final ds.manual.interpreter.VState vheap_in3243 = _7;
		final boolean bool_in2703 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2883 = _9;
		final I_Strategy s11430 = this._1;
		{
			if (t_in2703 instanceof org.spoofax.interpreter.terms.IStrategoTuple) {
				final org.spoofax.interpreter.terms.IStrategoTuple t97917 = (org.spoofax.interpreter.terms.IStrategoTuple) t_in2703;
				final org.spoofax.interpreter.terms.IStrategoTerm[] ts5862 = t97917
						.getAllSubterms();
				final org.spoofax.interpreter.terms.IStrategoList lifted_31077 = tf_in2703
						.makeList(ts5862);
				final I_Somer lifted_31087 = new oneOrMore_1(null, s11430);
				final R_some_TLIST $tmp2476 = lifted_31087.exec_some(ic_in2703,
						senv_in3063, venv_in3153, tf_in2703, lifted_31077,
						sheap_in3603, vheap_in3243, trace_in2883, false);
				final org.spoofax.interpreter.terms.IStrategoList ats_115 = $tmp2476.value;
				final ds.manual.interpreter.SState sheap_29532 = $tmp2476
						.get_1();
				final ds.manual.interpreter.VState vheap_28886 = $tmp2476
						.get_2();
				final org.spoofax.interpreter.core.StackTracer trace_28510 = $tmp2476
						.get_3();
				final boolean bool_28447 = $tmp2476.get_4();
				if (bool_28447 == true) {
					final org.spoofax.interpreter.terms.IStrategoTerm[] lifted_31107 = ats_115
							.getAllSubterms();
					final org.spoofax.interpreter.terms.IStrategoTuple t_104 = tf_in2703
							.makeTuple(lifted_31107);
					final org.spoofax.interpreter.terms.IStrategoList lifted_31117 = t97917
							.getAnnotations();
					final org.spoofax.interpreter.terms.IStrategoTerm t__59 = tf_in2703
							.annotateTerm(t_104, lifted_31117);
					final IValue lifted_31067 = new S_1(null, t__59);
					final ds.manual.interpreter.SState sheap_out3603 = sheap_29532;
					final ds.manual.interpreter.VState vheap_out3243 = vheap_28886;
					final boolean bool_out2703 = bool_28447;
					final org.spoofax.interpreter.core.StackTracer trace_out2883 = trace_28510;
					final IValue result_out4503 = lifted_31067;
					return new R_default_Value(result_out4503, sheap_out3603,
							vheap_out3243, bool_out2703, trace_out2883);
				} else {
				}
			} else {
				if (t_in2703 instanceof org.spoofax.interpreter.terms.IStrategoList) {
					final org.spoofax.interpreter.terms.IStrategoList t97927 = (org.spoofax.interpreter.terms.IStrategoList) t_in2703;
					final org.spoofax.interpreter.terms.IStrategoTerm[] ts5863 = t97927
							.getAllSubterms();
					final org.spoofax.interpreter.terms.IStrategoList ats3823 = tf_in2703
							.makeList(ts5863);
					final I_Somer lifted_31137 = new oneOrMore_1(null, s11430);
					final R_some_TLIST $tmp2477 = lifted_31137.exec_some(
							ic_in2703, senv_in3063, venv_in3153, tf_in2703,
							ats3823, sheap_in3603, vheap_in3243, trace_in2883,
							false);
					final org.spoofax.interpreter.terms.IStrategoList ats_116 = $tmp2477.value;
					final ds.manual.interpreter.SState sheap_29533 = $tmp2477
							.get_1();
					final ds.manual.interpreter.VState vheap_28887 = $tmp2477
							.get_2();
					final org.spoofax.interpreter.core.StackTracer trace_28511 = $tmp2477
							.get_3();
					final boolean bool_28448 = $tmp2477.get_4();
					if (bool_28448 == true) {
						final org.spoofax.interpreter.terms.IStrategoList lifted_31157 = t97927
								.getAnnotations();
						final org.spoofax.interpreter.terms.IStrategoTerm ats__27 = tf_in2703
								.annotateTerm(ats_116, lifted_31157);
						final IValue lifted_31127 = new S_1(null, ats__27);
						final ds.manual.interpreter.SState sheap_out3603 = sheap_29533;
						final ds.manual.interpreter.VState vheap_out3243 = vheap_28887;
						final boolean bool_out2703 = bool_28448;
						final org.spoofax.interpreter.core.StackTracer trace_out2883 = trace_28511;
						final IValue result_out4503 = lifted_31127;
						return new R_default_Value(result_out4503,
								sheap_out3603, vheap_out3243, bool_out2703,
								trace_out2883);
					} else {
					}
				} else {
					if (t_in2703 instanceof org.spoofax.interpreter.terms.IStrategoAppl) {
						final org.spoofax.interpreter.terms.IStrategoAppl t97937 = (org.spoofax.interpreter.terms.IStrategoAppl) t_in2703;
						final org.spoofax.interpreter.terms.IStrategoConstructor ac1181 = t97937
								.getConstructor();
						final org.spoofax.interpreter.terms.IStrategoTerm[] ts5864 = t97937
								.getAllSubterms();
						final org.spoofax.interpreter.terms.IStrategoList ats3824 = tf_in2703
								.makeList(ts5864);
						final I_Somer lifted_31177 = new oneOrMore_1(null,
								s11430);
						final R_some_TLIST $tmp2478 = lifted_31177.exec_some(
								ic_in2703, senv_in3063, venv_in3153, tf_in2703,
								ats3824, sheap_in3603, vheap_in3243,
								trace_in2883, false);
						final org.spoofax.interpreter.terms.IStrategoList ats_117 = $tmp2478.value;
						final ds.manual.interpreter.SState sheap_29534 = $tmp2478
								.get_1();
						final ds.manual.interpreter.VState vheap_28888 = $tmp2478
								.get_2();
						final org.spoofax.interpreter.core.StackTracer trace_28512 = $tmp2478
								.get_3();
						final boolean bool_28449 = $tmp2478.get_4();
						if (bool_28449 == true) {
							final org.spoofax.interpreter.terms.IStrategoTerm[] ts_102 = ats_117
									.getAllSubterms();
							final org.spoofax.interpreter.terms.IStrategoAppl t_105 = tf_in2703
									.makeAppl(ac1181, ts_102);
							final org.spoofax.interpreter.terms.IStrategoList lifted_31197 = t97937
									.getAnnotations();
							final org.spoofax.interpreter.terms.IStrategoTerm t__60 = tf_in2703
									.annotateTerm(t_105, lifted_31197);
							final IValue lifted_31167 = new S_1(null, t__60);
							final ds.manual.interpreter.SState sheap_out3603 = sheap_29534;
							final ds.manual.interpreter.VState vheap_out3243 = vheap_28888;
							final boolean bool_out2703 = bool_28449;
							final org.spoofax.interpreter.core.StackTracer trace_out2883 = trace_28512;
							final IValue result_out4503 = lifted_31167;
							return new R_default_Value(result_out4503,
									sheap_out3603, vheap_out3243, bool_out2703,
									trace_out2883);
						} else {
						}
					} else {
					}
				}
			}
		}
		{
			final IValue lifted_31207 = new F_0(null);
			final ds.manual.interpreter.SState sheap_out3603 = sheap_in3603;
			final ds.manual.interpreter.VState vheap_out3243 = vheap_in3243;
			final boolean bool_out2703 = bool_in2703;
			final org.spoofax.interpreter.core.StackTracer trace_out2883 = trace_in2883;
			final IValue result_out4503 = lifted_31207;
			return new R_default_Value(result_out4503, sheap_out3603,
					vheap_out3243, bool_out2703, trace_out2883);
		}
	}

	public I_Strategy get_1() {
		return this._1;
	}
}
package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class All_1 extends AbstractNode implements I_Strategy {
	private boolean hasSpecialized;

	@Child
	public I_Strategy _1;

	public All_1(INodeSource source, I_Strategy _1) {
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
		final All_1 other = (All_1) obj;
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2701 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3061 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3151 = _3;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2701 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2701 = _5;
		final ds.manual.interpreter.SState sheap_in3601 = _6;
		final ds.manual.interpreter.VState vheap_in3241 = _7;
		final boolean bool_in2701 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2881 = _9;
		final I_Strategy s11428 = this._1;
		{
			if (t_in2701 instanceof org.spoofax.interpreter.terms.IStrategoInt) {
				final org.spoofax.interpreter.terms.IStrategoInt t98197 = (org.spoofax.interpreter.terms.IStrategoInt) t_in2701;
				final IValue lifted_35457 = new S_1(null, t98197);
				final ds.manual.interpreter.SState sheap_out3601 = sheap_in3601;
				final ds.manual.interpreter.VState vheap_out3241 = vheap_in3241;
				final boolean bool_out2701 = bool_in2701;
				final org.spoofax.interpreter.core.StackTracer trace_out2881 = trace_in2881;
				final IValue result_out4501 = lifted_35457;
				return new R_default_Value(result_out4501, sheap_out3601,
						vheap_out3241, bool_out2701, trace_out2881);
			} else {
				if (t_in2701 instanceof org.spoofax.interpreter.terms.IStrategoReal) {
					final org.spoofax.interpreter.terms.IStrategoReal t98207 = (org.spoofax.interpreter.terms.IStrategoReal) t_in2701;
					final IValue lifted_35467 = new S_1(null, t98207);
					final ds.manual.interpreter.SState sheap_out3601 = sheap_in3601;
					final ds.manual.interpreter.VState vheap_out3241 = vheap_in3241;
					final boolean bool_out2701 = bool_in2701;
					final org.spoofax.interpreter.core.StackTracer trace_out2881 = trace_in2881;
					final IValue result_out4501 = lifted_35467;
					return new R_default_Value(result_out4501, sheap_out3601,
							vheap_out3241, bool_out2701, trace_out2881);
				} else {
					if (t_in2701 instanceof org.spoofax.interpreter.terms.IStrategoString) {
						final org.spoofax.interpreter.terms.IStrategoString t98217 = (org.spoofax.interpreter.terms.IStrategoString) t_in2701;
						final IValue lifted_35477 = new S_1(null, t98217);
						final ds.manual.interpreter.SState sheap_out3601 = sheap_in3601;
						final ds.manual.interpreter.VState vheap_out3241 = vheap_in3241;
						final boolean bool_out2701 = bool_in2701;
						final org.spoofax.interpreter.core.StackTracer trace_out2881 = trace_in2881;
						final IValue result_out4501 = lifted_35477;
						return new R_default_Value(result_out4501,
								sheap_out3601, vheap_out3241, bool_out2701,
								trace_out2881);
					} else {
						if (t_in2701 instanceof org.spoofax.interpreter.terms.IStrategoTuple) {
							final org.spoofax.interpreter.terms.IStrategoTuple t98227 = (org.spoofax.interpreter.terms.IStrategoTuple) t_in2701;
							final org.spoofax.interpreter.terms.IStrategoTerm[] ts5857 = t98227
									.getAllSubterms();
							final org.spoofax.interpreter.terms.IStrategoList ats3818 = tf_in2701
									.makeList(ts5857);
							final I_Mapper lifted_35497 = new map_1(null,
									s11428);
							final R_map_Value $tmp2464 = lifted_35497.exec_map(
									ic_in2701, senv_in3061, venv_in3151,
									tf_in2701, ats3818, sheap_in3601,
									vheap_in3241, bool_in2701, trace_in2881);
							final IValue lifted_35507 = $tmp2464.value;
							final ds.manual.interpreter.SState sheap_29526 = $tmp2464
									.get_1();
							final ds.manual.interpreter.VState vheap_28880 = $tmp2464
									.get_2();
							final boolean bool_28441 = $tmp2464.get_3();
							final org.spoofax.interpreter.core.StackTracer trace_28504 = $tmp2464
									.get_4();
							final S_1 $tmp2465 = lifted_35507.match(S_1.class);
							if ($tmp2465 != null) {
								final org.spoofax.interpreter.terms.IStrategoTerm ats_110 = $tmp2465
										.get_1();
								if (ats_110 instanceof org.spoofax.interpreter.terms.IStrategoList) {
									final org.spoofax.interpreter.terms.IStrategoList ats_34880 = (org.spoofax.interpreter.terms.IStrategoList) ats_110;
									final org.spoofax.interpreter.terms.IStrategoTerm[] ts_99 = ats_34880
											.getAllSubterms();
									final org.spoofax.interpreter.terms.IStrategoTuple t_100 = tf_in2701
											.makeTuple(ts_99);
									final org.spoofax.interpreter.terms.IStrategoList lifted_35517 = t98227
											.getAnnotations();
									final org.spoofax.interpreter.terms.IStrategoTerm t__55 = tf_in2701
											.annotateTerm(t_100, lifted_35517);
									final IValue lifted_35487 = new S_1(null,
											t__55);
									final ds.manual.interpreter.SState sheap_out3601 = sheap_29526;
									final ds.manual.interpreter.VState vheap_out3241 = vheap_28880;
									final boolean bool_out2701 = bool_28441;
									final org.spoofax.interpreter.core.StackTracer trace_out2881 = trace_28504;
									final IValue result_out4501 = lifted_35487;
									return new R_default_Value(result_out4501,
											sheap_out3601, vheap_out3241,
											bool_out2701, trace_out2881);
								} else {
								}
							} else {
							}
						} else {
							if (t_in2701 instanceof org.spoofax.interpreter.terms.IStrategoList) {
								final org.spoofax.interpreter.terms.IStrategoList t98237 = (org.spoofax.interpreter.terms.IStrategoList) t_in2701;
								final I_Mapper lifted_35537 = new map_1(null,
										s11428);
								final R_map_Value $tmp2466 = lifted_35537
										.exec_map(ic_in2701, senv_in3061,
												venv_in3151, tf_in2701, t98237,
												sheap_in3601, vheap_in3241,
												bool_in2701, trace_in2881);
								final IValue lifted_35547 = $tmp2466.value;
								final ds.manual.interpreter.SState sheap_29527 = $tmp2466
										.get_1();
								final ds.manual.interpreter.VState vheap_28881 = $tmp2466
										.get_2();
								final boolean bool_28442 = $tmp2466.get_3();
								final org.spoofax.interpreter.core.StackTracer trace_28505 = $tmp2466
										.get_4();
								final S_1 $tmp2467 = lifted_35547
										.match(S_1.class);
								if ($tmp2467 != null) {
									final org.spoofax.interpreter.terms.IStrategoTerm ats_111 = $tmp2467
											.get_1();
									final org.spoofax.interpreter.terms.IStrategoList lifted_35557 = t98237
											.getAnnotations();
									final org.spoofax.interpreter.terms.IStrategoTerm ats__25 = tf_in2701
											.annotateTerm(ats_111, lifted_35557);
									final IValue lifted_35527 = new S_1(null,
											ats__25);
									final ds.manual.interpreter.SState sheap_out3601 = sheap_29527;
									final ds.manual.interpreter.VState vheap_out3241 = vheap_28881;
									final boolean bool_out2701 = bool_28442;
									final org.spoofax.interpreter.core.StackTracer trace_out2881 = trace_28505;
									final IValue result_out4501 = lifted_35527;
									return new R_default_Value(result_out4501,
											sheap_out3601, vheap_out3241,
											bool_out2701, trace_out2881);
								} else {
								}
							} else {
								if (t_in2701 instanceof org.spoofax.interpreter.terms.IStrategoAppl) {
									final org.spoofax.interpreter.terms.IStrategoAppl t98247 = (org.spoofax.interpreter.terms.IStrategoAppl) t_in2701;
									final org.spoofax.interpreter.terms.IStrategoConstructor ac1179 = t98247
											.getConstructor();
									final org.spoofax.interpreter.terms.IStrategoTerm[] ts5858 = t98247
											.getAllSubterms();
									final org.spoofax.interpreter.terms.IStrategoList ats3819 = tf_in2701
											.makeList(ts5858);
									final I_Mapper lifted_35577 = new map_1(
											null, s11428);
									final R_map_Value $tmp2468 = lifted_35577
											.exec_map(ic_in2701, senv_in3061,
													venv_in3151, tf_in2701,
													ats3819, sheap_in3601,
													vheap_in3241, bool_in2701,
													trace_in2881);
									final IValue lifted_35587 = $tmp2468.value;
									final ds.manual.interpreter.SState sheap_29528 = $tmp2468
											.get_1();
									final ds.manual.interpreter.VState vheap_28882 = $tmp2468
											.get_2();
									final boolean bool_28443 = $tmp2468.get_3();
									final org.spoofax.interpreter.core.StackTracer trace_28506 = $tmp2468
											.get_4();
									final S_1 $tmp2469 = lifted_35587
											.match(S_1.class);
									if ($tmp2469 != null) {
										final org.spoofax.interpreter.terms.IStrategoTerm ats_112 = $tmp2469
												.get_1();
										if (ats_112 instanceof org.spoofax.interpreter.terms.IStrategoList) {
											final org.spoofax.interpreter.terms.IStrategoList ats_34890 = (org.spoofax.interpreter.terms.IStrategoList) ats_112;
											final org.spoofax.interpreter.terms.IStrategoTerm[] ts_100 = ats_34890
													.getAllSubterms();
											final org.spoofax.interpreter.terms.IStrategoAppl t_101 = tf_in2701
													.makeAppl(ac1179, ts_100);
											final org.spoofax.interpreter.terms.IStrategoList lifted_35597 = t98247
													.getAnnotations();
											final org.spoofax.interpreter.terms.IStrategoTerm t__56 = tf_in2701
													.annotateTerm(t_101,
															lifted_35597);
											final IValue lifted_35567 = new S_1(
													null, t__56);
											final ds.manual.interpreter.SState sheap_out3601 = sheap_29528;
											final ds.manual.interpreter.VState vheap_out3241 = vheap_28882;
											final boolean bool_out2701 = bool_28443;
											final org.spoofax.interpreter.core.StackTracer trace_out2881 = trace_28506;
											final IValue result_out4501 = lifted_35567;
											return new R_default_Value(
													result_out4501,
													sheap_out3601,
													vheap_out3241,
													bool_out2701, trace_out2881);
										} else {
										}
									} else {
									}
								} else {
								}
							}
						}
					}
				}
			}
		}
		{
			final IValue lifted_35607 = new F_0(null);
			final ds.manual.interpreter.SState sheap_out3601 = sheap_in3601;
			final ds.manual.interpreter.VState vheap_out3241 = vheap_in3241;
			final boolean bool_out2701 = bool_in2701;
			final org.spoofax.interpreter.core.StackTracer trace_out2881 = trace_in2881;
			final IValue result_out4501 = lifted_35607;
			return new R_default_Value(result_out4501, sheap_out3601,
					vheap_out3241, bool_out2701, trace_out2881);
		}
	}

	public I_Strategy get_1() {
		return this._1;
	}
}
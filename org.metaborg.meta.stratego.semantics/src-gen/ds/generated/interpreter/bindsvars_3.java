package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class bindsvars_3 extends AbstractNode implements I_Binder {
	private boolean hasSpecialized;

	public INodeList<String> _1;

	@Children
	public INodeList<I_Strategy> _2;

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _3;

	public bindsvars_3(
			INodeSource source,
			INodeList<String> _1,
			INodeList<I_Strategy> _2,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _3) {
		this.setSourceInfo(source);
		this._1 = _1;
		this._2 = adoptChildren(_2);
		this._3 = _3;
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
		final bindsvars_3 other = (bindsvars_3) obj;
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
			for (I_Strategy _2_elem : _2) {
				if (_2_elem instanceof IGenericNode) {
					((IGenericNode) _2_elem).specialize(depth);
				}
			}
			hasSpecialized = true;
		}
	}

	public R_bindtvars_VEnv exec_bindtvars(
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
			ds.manual.interpreter.VState _2) {
		this.specializeChildren(0);
		throw new InterpreterException("Rule failure");
	}

	public R_bindsvars_SEnv exec_bindsvars(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			org.spoofax.interpreter.terms.IStrategoTerm _2,
			org.spoofax.interpreter.terms.ITermFactory _3,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _4,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _5,
			boolean _6, org.spoofax.interpreter.core.StackTracer _7,
			ds.manual.interpreter.VState _8, ds.manual.interpreter.SState _9) {
		this.specializeChildren(0);
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2727 = _1;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2727 = _2;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2727 = _3;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3178 = _4;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3087 = _5;
		final boolean bool_in2727 = _6;
		final org.spoofax.interpreter.core.StackTracer trace_in2907 = _7;
		final ds.manual.interpreter.VState vheap_in3268 = _8;
		final ds.manual.interpreter.SState sheap_in3627 = _9;
		final INodeList<String> lifted_34447 = this._1;
		final INodeList<I_Strategy> lifted_34457 = this._2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> d_into486 = this._3;
		{
			if (lifted_34447 != null
					&& lifted_34447.equals(NodeList.NIL(Object.class))) {
				if (lifted_34457 != null
						&& lifted_34457.equals(NodeList.NIL(Object.class))) {
					final boolean bool_out2727 = bool_in2727;
					final org.spoofax.interpreter.core.StackTracer trace_out2907 = trace_in2907;
					final ds.manual.interpreter.VState vheap_out3268 = vheap_in3268;
					final ds.manual.interpreter.SState sheap_out3627 = sheap_in3627;
					final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> result_out4528 = d_into486;
					return new R_bindsvars_SEnv(result_out4528, bool_out2727,
							trace_out2907, vheap_out3268, sheap_out3627);
				} else {
				}
			} else {
				if (lifted_34447 != null && !lifted_34447.isEmpty()) {
					final String x3753 = lifted_34447.head();
					final INodeList<String> xs1000 = lifted_34447.tail();
					if (lifted_34457 != null && !lifted_34457.isEmpty()) {
						final I_Strategy s11440 = lifted_34457.head();
						final INodeList<I_Strategy> ss867 = lifted_34457.tail();
						final CallT_3 $tmp2641 = s11440.match(CallT_3.class);
						if ($tmp2641 != null) {
							final I_SVar lifted_34397 = $tmp2641.get_1();
							final INodeList<I_Strategy> ass1008 = $tmp2641
									.get_2();
							final INodeList<I_STerm> ats3828 = $tmp2641.get_3();
							final SVar_1 $tmp2642 = lifted_34397
									.match(SVar_1.class);
							if ($tmp2642 != null) {
								final String tgt477 = $tmp2642.get_1();
								if (ds.manual.interpreter.Natives.boolAnd_2(
										ds.manual.interpreter.Natives
												.isEmptyStrategies_1(ass1008),
										ds.manual.interpreter.Natives
												.isEmpty_1(ats3828)) == true) {
									final I_SHeapOp lifted_34217 = new SLookup_2(
											null, senv_in3087, tgt477);
									final R_slook_SLookupResult $tmp2643 = lifted_34217
											.exec_slook(sheap_in3627);
									final I_SLookupResult lifted_34227 = $tmp2643.value;
									final ds.manual.interpreter.SState sheap_29577 = $tmp2643
											.get_1();
									final SLookupResult_2 $tmp2644 = lifted_34227
											.match(SLookupResult_2.class);
									if ($tmp2644 != null) {
										final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> lifted_30617 = $tmp2644
												.get_1();
										final I_Thunk lifted_34267 = $tmp2644
												.get_2();
										final Thunk_6 $tmp2645 = lifted_34267
												.match(Thunk_6.class);
										if ($tmp2645 != null) {
											final String lifted_30627 = $tmp2645
													.get_1();
											final INodeList<String> fss568 = $tmp2645
													.get_2();
											final INodeList<String> fts568 = $tmp2645
													.get_3();
											final I_Strategy sactual378 = $tmp2645
													.get_4();
											final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> e_tgt378 = $tmp2645
													.get_5();
											final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> d_tgt378 = $tmp2645
													.get_6();
											final I_Thunk lifted_34277 = new Thunk_6(
													null, x3753, fss568,
													fts568, sactual378,
													e_tgt378, d_tgt378);
											final I_SHeapOp lifted_34237 = new SPush_3(
													null, d_into486, x3753,
													lifted_34277);
											final R_salloc_SEnv $tmp2646 = lifted_34237
													.exec_salloc(sheap_29577);
											final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> d_into_30 = $tmp2646.value;
											final ds.manual.interpreter.SState sheap_34717 = $tmp2646
													.get_1();
											final I_Binder lifted_34247 = new bindsvars_3(
													null, xs1000, ss867,
													d_into_30);
											final R_bindsvars_SEnv $tmp2647 = lifted_34247
													.exec_bindsvars(ic_in2727,
															t_in2727,
															tf_in2727,
															venv_in3178,
															senv_in3087,
															bool_in2727,
															trace_in2907,
															vheap_in3268,
															sheap_34717);
											final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> d_into__30 = $tmp2647.value;
											final boolean bool_28492 = $tmp2647
													.get_1();
											final org.spoofax.interpreter.core.StackTracer trace_28555 = $tmp2647
													.get_2();
											final ds.manual.interpreter.VState vheap_28933 = $tmp2647
													.get_3();
											final ds.manual.interpreter.SState sheap_4558 = $tmp2647
													.get_4();
											final boolean bool_out2727 = bool_28492;
											final org.spoofax.interpreter.core.StackTracer trace_out2907 = trace_28555;
											final ds.manual.interpreter.VState vheap_out3268 = vheap_28933;
											final ds.manual.interpreter.SState sheap_out3627 = sheap_4558;
											final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> result_out4528 = d_into__30;
											return new R_bindsvars_SEnv(
													result_out4528,
													bool_out2727,
													trace_out2907,
													vheap_out3268,
													sheap_out3627);
										} else {
										}
									} else {
									}
								} else {
									if (ds.manual.interpreter.Natives
											.boolAnd_2(
													ds.manual.interpreter.Natives
															.isEmptyStrategies_1(ass1008),
													ds.manual.interpreter.Natives
															.isEmpty_1(ats3828)) == false) {
										final I_SHeapOp lifted_34317 = new SLookup_2(
												null, senv_in3087, tgt477);
										final R_slook_SLookupResult $tmp2648 = lifted_34317
												.exec_slook(sheap_in3627);
										final I_SLookupResult lifted_34327 = $tmp2648.value;
										final ds.manual.interpreter.SState sheap_29578 = $tmp2648
												.get_1();
										final SLookupResult_2 $tmp2649 = lifted_34327
												.match(SLookupResult_2.class);
										if ($tmp2649 != null) {
											final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> lifted_30637 = $tmp2649
													.get_1();
											final I_Thunk lifted_34407 = $tmp2649
													.get_2();
											final Thunk_6 $tmp2650 = lifted_34407
													.match(Thunk_6.class);
											if ($tmp2650 != null) {
												final String lifted_30647 = $tmp2650
														.get_1();
												final INodeList<String> fss569 = $tmp2650
														.get_2();
												final INodeList<String> fts569 = $tmp2650
														.get_3();
												final I_Strategy sactual379 = $tmp2650
														.get_4();
												final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> e_tgt379 = $tmp2650
														.get_5();
												final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> d_tgt379 = $tmp2650
														.get_6();
												final I_Builder lifted_34337 = new bl_1(
														null, ats3828);
												final R_blds_BuildRes $tmp2651 = lifted_34337
														.exec_blds(ic_in2727,
																senv_in3087,
																venv_in3178,
																t_in2727,
																tf_in2727,
																sheap_29578,
																vheap_in3268,
																bool_in2727,
																trace_in2907);
												final I_BuildRes lifted_34347 = $tmp2651.value;
												final ds.manual.interpreter.SState sheap_34718 = $tmp2651
														.get_1();
												final ds.manual.interpreter.VState vheap_28934 = $tmp2651
														.get_2();
												final boolean bool_28493 = $tmp2651
														.get_3();
												final org.spoofax.interpreter.core.StackTracer trace_28556 = $tmp2651
														.get_4();
												final BSS_1 $tmp2652 = lifted_34347
														.match(BSS_1.class);
												if ($tmp2652 != null) {
													final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> ats_120 = $tmp2652
															.get_1();
													final I_Binder lifted_34357 = new bindtvars_2(
															null, fts569,
															ats_120);
													final R_bindtvars_VEnv $tmp2653 = lifted_34357
															.exec_bindtvars(
																	e_tgt379,
																	vheap_28934);
													final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> e_tgt_10 = $tmp2653.value;
													final ds.manual.interpreter.VState vheap_34255 = $tmp2653
															.get_1();
													final I_Binder lifted_34367 = new bindsvars_3(
															null, fss569,
															ass1008, d_tgt379);
													final R_bindsvars_SEnv $tmp2654 = lifted_34367
															.exec_bindsvars(
																	ic_in2727,
																	t_in2727,
																	tf_in2727,
																	venv_in3178,
																	senv_in3087,
																	bool_28493,
																	trace_28556,
																	vheap_34255,
																	sheap_34718);
													final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> d_tgt_10 = $tmp2654.value;
													final boolean bool_33373 = $tmp2654
															.get_1();
													final org.spoofax.interpreter.core.StackTracer trace_33694 = $tmp2654
															.get_2();
													final ds.manual.interpreter.VState vheap_4419 = $tmp2654
															.get_3();
													final ds.manual.interpreter.SState sheap_4559 = $tmp2654
															.get_4();
													final I_Thunk lifted_34417 = new Thunk_6(
															null,
															x3753,
															NodeList.NIL(String.class),
															NodeList.NIL(String.class),
															sactual379,
															e_tgt_10, d_tgt_10);
													final I_SHeapOp lifted_34377 = new SPush_3(
															null, d_into486,
															x3753, lifted_34417);
													final R_salloc_SEnv $tmp2655 = lifted_34377
															.exec_salloc(sheap_4559);
													final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> d_into_31 = $tmp2655.value;
													final ds.manual.interpreter.SState sheap_5279 = $tmp2655
															.get_1();
													final I_Binder lifted_34387 = new bindsvars_3(
															null, xs1000,
															ss867, d_into_31);
													final R_bindsvars_SEnv $tmp2656 = lifted_34387
															.exec_bindsvars(
																	ic_in2727,
																	t_in2727,
																	tf_in2727,
																	venv_in3178,
																	senv_in3087,
																	bool_33373,
																	trace_33694,
																	vheap_4419,
																	sheap_5279);
													final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> d_into__31 = $tmp2656.value;
													final boolean bool_4279 = $tmp2656
															.get_1();
													final org.spoofax.interpreter.core.StackTracer trace_4418 = $tmp2656
															.get_2();
													final ds.manual.interpreter.VState vheap_5279 = $tmp2656
															.get_3();
													final ds.manual.interpreter.SState sheap_6139 = $tmp2656
															.get_4();
													final boolean bool_out2727 = bool_4279;
													final org.spoofax.interpreter.core.StackTracer trace_out2907 = trace_4418;
													final ds.manual.interpreter.VState vheap_out3268 = vheap_5279;
													final ds.manual.interpreter.SState sheap_out3627 = sheap_6139;
													final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> result_out4528 = d_into__31;
													return new R_bindsvars_SEnv(
															result_out4528,
															bool_out2727,
															trace_out2907,
															vheap_out3268,
															sheap_out3627);
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
						} else {
							if ($antimatch20(s11440)) {
								final I_Thunk lifted_34487 = new Thunk_6(null,
										x3753, NodeList.NIL(String.class),
										NodeList.NIL(String.class), s11440,
										venv_in3178, senv_in3087);
								final I_SHeapOp lifted_34467 = new SPush_3(
										null, d_into486, x3753, lifted_34487);
								final R_salloc_SEnv $tmp2660 = lifted_34467
										.exec_salloc(sheap_in3627);
								final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> d_into_32 = $tmp2660.value;
								final ds.manual.interpreter.SState sheap_29579 = $tmp2660
										.get_1();
								final I_Binder lifted_34477 = new bindsvars_3(
										null, xs1000, ss867, d_into_32);
								final R_bindsvars_SEnv $tmp2661 = lifted_34477
										.exec_bindsvars(ic_in2727, t_in2727,
												tf_in2727, venv_in3178,
												senv_in3087, bool_in2727,
												trace_in2907, vheap_in3268,
												sheap_29579);
								final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> d_into__32 = $tmp2661.value;
								final boolean bool_28494 = $tmp2661.get_1();
								final org.spoofax.interpreter.core.StackTracer trace_28557 = $tmp2661
										.get_2();
								final ds.manual.interpreter.VState vheap_28935 = $tmp2661
										.get_3();
								final ds.manual.interpreter.SState sheap_34719 = $tmp2661
										.get_4();
								final boolean bool_out2727 = bool_28494;
								final org.spoofax.interpreter.core.StackTracer trace_out2907 = trace_28557;
								final ds.manual.interpreter.VState vheap_out3268 = vheap_28935;
								final ds.manual.interpreter.SState sheap_out3627 = sheap_34719;
								final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> result_out4528 = d_into__32;
								return new R_bindsvars_SEnv(result_out4528,
										bool_out2727, trace_out2907,
										vheap_out3268, sheap_out3627);
							} else {
							}
						}
					} else {
					}
				} else {
				}
			}
		}
		{
			throw new InterpreterException("Rule failure");
		}
	}

	private boolean $antimatch20(I_Strategy $tmp2657) {
		final CallT_3 $tmp2658 = $tmp2657.match(CallT_3.class);
		if ($tmp2658 != null) {
			final I_SVar lifted_3561 = $tmp2658.get_1();
			final INodeList<I_Strategy> lifted_3562 = $tmp2658.get_2();
			final INodeList<I_STerm> lifted_3563 = $tmp2658.get_3();
			final SVar_1 $tmp2659 = lifted_3561.match(SVar_1.class);
			if ($tmp2659 != null) {
				final String lifted_3564 = $tmp2659.get_1();
				final String lifted_30657 = lifted_3564;
				final INodeList<I_Strategy> lifted_30667 = lifted_3562;
				final INodeList<I_STerm> lifted_30677 = lifted_3563;
				return false;
			} else {
			}
		} else {
		}
		return true;
	}

	public INodeList<String> get_1() {
		return this._1;
	}

	public INodeList<I_Strategy> get_2() {
		return this._2;
	}

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> get_3() {
		return this._3;
	}
}
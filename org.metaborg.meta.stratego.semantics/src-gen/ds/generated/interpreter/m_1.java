package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class m_1 extends AbstractNode implements I_Matcher {
	private boolean hasSpecialized;

	@Child
	public I_STerm _1;

	public m_1(INodeSource source, I_STerm _1) {
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
		final m_1 other = (m_1) obj;
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

	public R_ma_Value exec_ma(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _3,
			org.spoofax.interpreter.terms.ITermFactory _4,
			org.spoofax.interpreter.terms.IStrategoTerm _5,
			ds.manual.interpreter.SState _6, ds.manual.interpreter.VState _7,
			boolean _8, org.spoofax.interpreter.core.StackTracer _9) {
		this.specializeChildren(0);
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2716 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3076 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3166 = _3;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2716 = _4;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2716 = _5;
		final ds.manual.interpreter.SState sheap_in3616 = _6;
		final ds.manual.interpreter.VState vheap_in3256 = _7;
		final boolean bool_in2716 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2896 = _9;
		final I_STerm op860 = this._1;
		{
			final Wld_0 $tmp2530 = op860.match(Wld_0.class);
			if ($tmp2530 != null) {
				final IValue lifted_32357 = new S_1(null, t_in2716);
				final ds.manual.interpreter.SState sheap_out3616 = sheap_in3616;
				final ds.manual.interpreter.VState vheap_out3256 = vheap_in3256;
				final boolean bool_out2716 = bool_in2716;
				final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_in2896;
				final IValue result_out4516 = lifted_32357;
				return new R_ma_Value(result_out4516, sheap_out3616,
						vheap_out3256, bool_out2716, trace_out2896);
			} else {
				final Int_1 $tmp2531 = op860.match(Int_1.class);
				if ($tmp2531 != null) {
					final String stringI355 = $tmp2531.get_1();
					if (t_in2716 instanceof org.spoofax.interpreter.terms.IStrategoInt) {
						final org.spoofax.interpreter.terms.IStrategoInt t98077 = (org.spoofax.interpreter.terms.IStrategoInt) t_in2716;
						final int i_20 = t98077.intValue();
						final int i160 = ds.manual.interpreter.Natives
								.parseInt_1(stringI355);
						if (i160 == i_20) {
							final IValue lifted_32377 = new S_1(null, t98077);
							final ds.manual.interpreter.SState sheap_out3616 = sheap_in3616;
							final ds.manual.interpreter.VState vheap_out3256 = vheap_in3256;
							final boolean bool_out2716 = bool_in2716;
							final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_in2896;
							final IValue result_out4516 = lifted_32377;
							return new R_ma_Value(result_out4516,
									sheap_out3616, vheap_out3256, bool_out2716,
									trace_out2896);
						} else {
						}
					} else {
					}
				} else {
					final Real_1 $tmp2532 = op860.match(Real_1.class);
					if ($tmp2532 != null) {
						final String stringR355 = $tmp2532.get_1();
						if (t_in2716 instanceof org.spoofax.interpreter.terms.IStrategoReal) {
							final org.spoofax.interpreter.terms.IStrategoReal t98087 = (org.spoofax.interpreter.terms.IStrategoReal) t_in2716;
							final double r_20 = t98087.realValue();
							final double r160 = ds.manual.interpreter.Natives
									.parseReal_1(stringR355);
							if (r160 == r_20) {
								final IValue lifted_32397 = new S_1(null,
										t98087);
								final ds.manual.interpreter.SState sheap_out3616 = sheap_in3616;
								final ds.manual.interpreter.VState vheap_out3256 = vheap_in3256;
								final boolean bool_out2716 = bool_in2716;
								final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_in2896;
								final IValue result_out4516 = lifted_32397;
								return new R_ma_Value(result_out4516,
										sheap_out3616, vheap_out3256,
										bool_out2716, trace_out2896);
							} else {
							}
						} else {
						}
					} else {
						final Str_1 $tmp2533 = op860.match(Str_1.class);
						if ($tmp2533 != null) {
							final String s11434 = $tmp2533.get_1();
							if (t_in2716 instanceof org.spoofax.interpreter.terms.IStrategoString) {
								final org.spoofax.interpreter.terms.IStrategoString t98097 = (org.spoofax.interpreter.terms.IStrategoString) t_in2716;
								final String s_20 = t98097.stringValue();
								if (s11434 != null && s11434.equals(s_20)) {
									final IValue lifted_32417 = new S_1(null,
											t98097);
									final ds.manual.interpreter.SState sheap_out3616 = sheap_in3616;
									final ds.manual.interpreter.VState vheap_out3256 = vheap_in3256;
									final boolean bool_out2716 = bool_in2716;
									final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_in2896;
									final IValue result_out4516 = lifted_32417;
									return new R_ma_Value(result_out4516,
											sheap_out3616, vheap_out3256,
											bool_out2716, trace_out2896);
								} else {
								}
							} else {
							}
						} else {
							final Var_1 $tmp2534 = op860.match(Var_1.class);
							if ($tmp2534 != null) {
								final String x3749 = $tmp2534.get_1();
								final I_VHeapOp lifted_32497 = new VLookup_2(
										null, venv_in3166, x3749);
								final R_vlook_VLookupResult $tmp2535 = lifted_32497
										.exec_vlook(vheap_in3256);
								final I_VLookupResult lifted_32507 = $tmp2535.value;
								final ds.manual.interpreter.VState vheap_28902 = $tmp2535
										.get_1();
								final VLookupResult_2 $tmp2536 = lifted_32507
										.match(VLookupResult_2.class);
								if ($tmp2536 != null) {
									final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> lifted_30247 = $tmp2536
											.get_1();
									final IValue lifted_32517 = $tmp2536
											.get_2();
									final F_0 $tmp2537 = lifted_32517
											.match(F_0.class);
									if ($tmp2537 != null) {
										final IValue v4124 = new S_1(null,
												t_in2716);
										final I_VHeapOp lifted_32457 = new VUpdate_3(
												null, lifted_30247, x3749,
												v4124);
										final R_vupdate_VEnv $tmp2538 = lifted_32457
												.exec_vupdate(vheap_28902);
										final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> lifted_30237 = $tmp2538.value;
										final ds.manual.interpreter.VState vheap_34237 = $tmp2538
												.get_1();
										final ds.manual.interpreter.SState sheap_out3616 = sheap_in3616;
										final ds.manual.interpreter.VState vheap_out3256 = vheap_34237;
										final boolean bool_out2716 = bool_in2716;
										final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_in2896;
										final IValue result_out4516 = v4124;
										return new R_ma_Value(result_out4516,
												sheap_out3616, vheap_out3256,
												bool_out2716, trace_out2896);
									} else {
										final S_1 $tmp2539 = lifted_32517
												.match(S_1.class);
										if ($tmp2539 != null) {
											final org.spoofax.interpreter.terms.IStrategoTerm t_107 = $tmp2539
													.get_1();
											if (t_in2716 != null
													&& t_in2716.equals(t_107)) {
												final IValue lifted_32487 = new S_1(
														null, t_in2716);
												final ds.manual.interpreter.SState sheap_out3616 = sheap_in3616;
												final ds.manual.interpreter.VState vheap_out3256 = vheap_28902;
												final boolean bool_out2716 = bool_in2716;
												final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_in2896;
												final IValue result_out4516 = lifted_32487;
												return new R_ma_Value(
														result_out4516,
														sheap_out3616,
														vheap_out3256,
														bool_out2716,
														trace_out2896);
											} else {
											}
										} else {
										}
									}
								} else {
								}
							} else {
								final As_2 $tmp2540 = op860.match(As_2.class);
								if ($tmp2540 != null) {
									final I_Var lifted_32647 = $tmp2540.get_1();
									final I_PreTerm p760 = $tmp2540.get_2();
									final Var_1 $tmp2541 = lifted_32647
											.match(Var_1.class);
									if ($tmp2541 != null) {
										final String x3750 = $tmp2541.get_1();
										final I_VHeapOp lifted_32657 = new VLookup_2(
												null, venv_in3166, x3750);
										final R_vlook_VLookupResult $tmp2542 = lifted_32657
												.exec_vlook(vheap_in3256);
										final I_VLookupResult lifted_32667 = $tmp2542.value;
										final ds.manual.interpreter.VState vheap_28903 = $tmp2542
												.get_1();
										final VLookupResult_2 $tmp2543 = lifted_32667
												.match(VLookupResult_2.class);
										if ($tmp2543 != null) {
											final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> lifted_30277 = $tmp2543
													.get_1();
											final IValue lifted_32697 = $tmp2543
													.get_2();
											final F_0 $tmp2544 = lifted_32697
													.match(F_0.class);
											if ($tmp2544 != null) {
												final I_Strategy lifted_32577 = new Match_1(
														null, p760);
												final R_default_Value $tmp2545 = lifted_32577
														.exec_default(
																ic_in2716,
																senv_in3076,
																venv_in3166,
																t_in2716,
																tf_in2716,
																sheap_in3616,
																vheap_28903,
																bool_in2716,
																trace_in2896);
												final IValue lifted_32587 = $tmp2545.value;
												final ds.manual.interpreter.SState sheap_29548 = $tmp2545
														.get_1();
												final ds.manual.interpreter.VState vheap_34238 = $tmp2545
														.get_2();
												final boolean bool_28463 = $tmp2545
														.get_3();
												final org.spoofax.interpreter.core.StackTracer trace_28526 = $tmp2545
														.get_4();
												final S_1 $tmp2546 = lifted_32587
														.match(S_1.class);
												if ($tmp2546 != null) {
													final org.spoofax.interpreter.terms.IStrategoTerm lifted_30257 = $tmp2546
															.get_1();
													final IValue lifted_32617 = new S_1(
															null, t_in2716);
													final I_VHeapOp lifted_32597 = new VUpdate_3(
															null, lifted_30277,
															x3750, lifted_32617);
													final R_vupdate_VEnv $tmp2547 = lifted_32597
															.exec_vupdate(vheap_34238);
													final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> lifted_30267 = $tmp2547.value;
													final ds.manual.interpreter.VState vheap_4418 = $tmp2547
															.get_1();
													final IValue lifted_32537 = new S_1(
															null, t_in2716);
													final ds.manual.interpreter.SState sheap_out3616 = sheap_29548;
													final ds.manual.interpreter.VState vheap_out3256 = vheap_4418;
													final boolean bool_out2716 = bool_28463;
													final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_28526;
													final IValue result_out4516 = lifted_32537;
													return new R_ma_Value(
															result_out4516,
															sheap_out3616,
															vheap_out3256,
															bool_out2716,
															trace_out2896);
												} else {
												}
											} else {
												final S_1 $tmp2548 = lifted_32697
														.match(S_1.class);
												if ($tmp2548 != null) {
													final org.spoofax.interpreter.terms.IStrategoTerm xt160 = $tmp2548
															.get_1();
													if (t_in2716 != null
															&& t_in2716
																	.equals(xt160)) {
														final I_Strategy lifted_32677 = new Match_1(
																null, p760);
														final R_default_Value $tmp2549 = lifted_32677
																.exec_default(
																		ic_in2716,
																		senv_in3076,
																		venv_in3166,
																		t_in2716,
																		tf_in2716,
																		sheap_in3616,
																		vheap_28903,
																		bool_in2716,
																		trace_in2896);
														final IValue lifted_32687 = $tmp2549.value;
														final ds.manual.interpreter.SState sheap_29549 = $tmp2549
																.get_1();
														final ds.manual.interpreter.VState vheap_34239 = $tmp2549
																.get_2();
														final boolean bool_28464 = $tmp2549
																.get_3();
														final org.spoofax.interpreter.core.StackTracer trace_28527 = $tmp2549
																.get_4();
														final S_1 $tmp2550 = lifted_32687
																.match(S_1.class);
														if ($tmp2550 != null) {
															final org.spoofax.interpreter.terms.IStrategoTerm lifted_30287 = $tmp2550
																	.get_1();
															final IValue lifted_32637 = new S_1(
																	null,
																	t_in2716);
															final ds.manual.interpreter.SState sheap_out3616 = sheap_29549;
															final ds.manual.interpreter.VState vheap_out3256 = vheap_34239;
															final boolean bool_out2716 = bool_28464;
															final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_28527;
															final IValue result_out4516 = lifted_32637;
															return new R_ma_Value(
																	result_out4516,
																	sheap_out3616,
																	vheap_out3256,
																	bool_out2716,
																	trace_out2896);
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
									}
								} else {
									final Explode_2 $tmp2551 = op860
											.match(Explode_2.class);
									if ($tmp2551 != null) {
										final I_STerm ct440 = $tmp2551.get_1();
										final I_STerm ts5865 = $tmp2551.get_2();
										if (t_in2716 instanceof org.spoofax.interpreter.terms.IStrategoAppl) {
											final org.spoofax.interpreter.terms.IStrategoAppl t98107 = (org.spoofax.interpreter.terms.IStrategoAppl) t_in2716;
											final org.spoofax.interpreter.terms.IStrategoConstructor lifted_32787 = t98107
													.getConstructor();
											final String lifted_32727 = lifted_32787
													.getName();
											final org.spoofax.interpreter.terms.IStrategoString constr870 = tf_in2716
													.makeString(lifted_32727);
											final I_Strategy lifted_32737 = new Match_1(
													null, ct440);
											final R_default_Value $tmp2552 = lifted_32737
													.exec_default(ic_in2716,
															senv_in3076,
															venv_in3166,
															constr870,
															tf_in2716,
															sheap_in3616,
															vheap_in3256,
															bool_in2716,
															trace_in2896);
											final IValue lifted_32747 = $tmp2552.value;
											final ds.manual.interpreter.SState sheap_29550 = $tmp2552
													.get_1();
											final ds.manual.interpreter.VState vheap_28904 = $tmp2552
													.get_2();
											final boolean bool_28465 = $tmp2552
													.get_3();
											final org.spoofax.interpreter.core.StackTracer trace_28528 = $tmp2552
													.get_4();
											final S_1 $tmp2553 = lifted_32747
													.match(S_1.class);
											if ($tmp2553 != null) {
												final org.spoofax.interpreter.terms.IStrategoTerm lifted_30297 = $tmp2553
														.get_1();
												final org.spoofax.interpreter.terms.IStrategoTerm[] lifted_32757 = t98107
														.getAllSubterms();
												final org.spoofax.interpreter.terms.IStrategoList kids515 = tf_in2716
														.makeList(lifted_32757);
												final I_Strategy lifted_32767 = new Match_1(
														null, ts5865);
												final R_default_Value $tmp2554 = lifted_32767
														.exec_default(
																ic_in2716,
																senv_in3076,
																venv_in3166,
																kids515,
																tf_in2716,
																sheap_29550,
																vheap_28904,
																bool_28465,
																trace_28528);
												final IValue lifted_32777 = $tmp2554.value;
												final ds.manual.interpreter.SState sheap_34703 = $tmp2554
														.get_1();
												final ds.manual.interpreter.VState vheap_34240 = $tmp2554
														.get_2();
												final boolean bool_33361 = $tmp2554
														.get_3();
												final org.spoofax.interpreter.core.StackTracer trace_33680 = $tmp2554
														.get_4();
												final S_1 $tmp2555 = lifted_32777
														.match(S_1.class);
												if ($tmp2555 != null) {
													final org.spoofax.interpreter.terms.IStrategoTerm lifted_30307 = $tmp2555
															.get_1();
													final IValue lifted_32717 = new S_1(
															null, t98107);
													final ds.manual.interpreter.SState sheap_out3616 = sheap_34703;
													final ds.manual.interpreter.VState vheap_out3256 = vheap_34240;
													final boolean bool_out2716 = bool_33361;
													final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_33680;
													final IValue result_out4516 = lifted_32717;
													return new R_ma_Value(
															result_out4516,
															sheap_out3616,
															vheap_out3256,
															bool_out2716,
															trace_out2896);
												} else {
												}
											} else {
											}
										} else {
											if (t_in2716 instanceof org.spoofax.interpreter.terms.IStrategoInt) {
												final org.spoofax.interpreter.terms.IStrategoInt t98117 = (org.spoofax.interpreter.terms.IStrategoInt) t_in2716;
												final I_Strategy lifted_32817 = new Match_1(
														null, ct440);
												final R_default_Value $tmp2556 = lifted_32817
														.exec_default(
																ic_in2716,
																senv_in3076,
																venv_in3166,
																t98117,
																tf_in2716,
																sheap_in3616,
																vheap_in3256,
																bool_in2716,
																trace_in2896);
												final IValue lifted_32827 = $tmp2556.value;
												final ds.manual.interpreter.SState sheap_29551 = $tmp2556
														.get_1();
												final ds.manual.interpreter.VState vheap_28905 = $tmp2556
														.get_2();
												final boolean bool_28466 = $tmp2556
														.get_3();
												final org.spoofax.interpreter.core.StackTracer trace_28529 = $tmp2556
														.get_4();
												final S_1 $tmp2557 = lifted_32827
														.match(S_1.class);
												if ($tmp2557 != null) {
													final org.spoofax.interpreter.terms.IStrategoTerm lifted_30317 = $tmp2557
															.get_1();
													final org.spoofax.interpreter.terms.IStrategoList lifted_32837 = ds.manual.interpreter.Natives
															.makeNil_1(tf_in2716);
													final I_Strategy lifted_32847 = new Match_1(
															null, ts5865);
													final R_default_Value $tmp2558 = lifted_32847
															.exec_default(
																	ic_in2716,
																	senv_in3076,
																	venv_in3166,
																	lifted_32837,
																	tf_in2716,
																	sheap_29551,
																	vheap_28905,
																	bool_28466,
																	trace_28529);
													final IValue lifted_32857 = $tmp2558.value;
													final ds.manual.interpreter.SState sheap_34704 = $tmp2558
															.get_1();
													final ds.manual.interpreter.VState vheap_34241 = $tmp2558
															.get_2();
													final boolean bool_33362 = $tmp2558
															.get_3();
													final org.spoofax.interpreter.core.StackTracer trace_33681 = $tmp2558
															.get_4();
													final S_1 $tmp2559 = lifted_32857
															.match(S_1.class);
													if ($tmp2559 != null) {
														final org.spoofax.interpreter.terms.IStrategoTerm lifted_30327 = $tmp2559
																.get_1();
														final IValue lifted_32807 = new S_1(
																null, t98117);
														final ds.manual.interpreter.SState sheap_out3616 = sheap_34704;
														final ds.manual.interpreter.VState vheap_out3256 = vheap_34241;
														final boolean bool_out2716 = bool_33362;
														final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_33681;
														final IValue result_out4516 = lifted_32807;
														return new R_ma_Value(
																result_out4516,
																sheap_out3616,
																vheap_out3256,
																bool_out2716,
																trace_out2896);
													} else {
													}
												} else {
												}
											} else {
												if (t_in2716 instanceof org.spoofax.interpreter.terms.IStrategoString) {
													final org.spoofax.interpreter.terms.IStrategoString t98127 = (org.spoofax.interpreter.terms.IStrategoString) t_in2716;
													final org.spoofax.interpreter.terms.IStrategoList lifted_32887 = ds.manual.interpreter.Natives
															.makeNil_1(tf_in2716);
													final org.spoofax.interpreter.terms.IStrategoTerm tnoanno160 = tf_in2716
															.annotateTerm(
																	t98127,
																	lifted_32887);
													final String lifted_32897 = tnoanno160
															.toString();
													final org.spoofax.interpreter.terms.IStrategoString constr871 = tf_in2716
															.makeString(lifted_32897);
													final I_Strategy lifted_32907 = new Match_1(
															null, ct440);
													final R_default_Value $tmp2560 = lifted_32907
															.exec_default(
																	ic_in2716,
																	senv_in3076,
																	venv_in3166,
																	constr871,
																	tf_in2716,
																	sheap_in3616,
																	vheap_in3256,
																	bool_in2716,
																	trace_in2896);
													final IValue lifted_32917 = $tmp2560.value;
													final ds.manual.interpreter.SState sheap_29552 = $tmp2560
															.get_1();
													final ds.manual.interpreter.VState vheap_28906 = $tmp2560
															.get_2();
													final boolean bool_28467 = $tmp2560
															.get_3();
													final org.spoofax.interpreter.core.StackTracer trace_28530 = $tmp2560
															.get_4();
													final S_1 $tmp2561 = lifted_32917
															.match(S_1.class);
													if ($tmp2561 != null) {
														final org.spoofax.interpreter.terms.IStrategoTerm lifted_30337 = $tmp2561
																.get_1();
														final org.spoofax.interpreter.terms.IStrategoList lifted_32927 = ds.manual.interpreter.Natives
																.makeNil_1(tf_in2716);
														final I_Strategy lifted_32937 = new Match_1(
																null, ts5865);
														final R_default_Value $tmp2562 = lifted_32937
																.exec_default(
																		ic_in2716,
																		senv_in3076,
																		venv_in3166,
																		lifted_32927,
																		tf_in2716,
																		sheap_29552,
																		vheap_28906,
																		bool_28467,
																		trace_28530);
														final IValue lifted_32947 = $tmp2562.value;
														final ds.manual.interpreter.SState sheap_34705 = $tmp2562
																.get_1();
														final ds.manual.interpreter.VState vheap_34242 = $tmp2562
																.get_2();
														final boolean bool_33363 = $tmp2562
																.get_3();
														final org.spoofax.interpreter.core.StackTracer trace_33682 = $tmp2562
																.get_4();
														final S_1 $tmp2563 = lifted_32947
																.match(S_1.class);
														if ($tmp2563 != null) {
															final org.spoofax.interpreter.terms.IStrategoTerm lifted_30347 = $tmp2563
																	.get_1();
															final IValue lifted_32877 = new S_1(
																	null,
																	t98127);
															final ds.manual.interpreter.SState sheap_out3616 = sheap_34705;
															final ds.manual.interpreter.VState vheap_out3256 = vheap_34242;
															final boolean bool_out2716 = bool_33363;
															final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_33682;
															final IValue result_out4516 = lifted_32877;
															return new R_ma_Value(
																	result_out4516,
																	sheap_out3616,
																	vheap_out3256,
																	bool_out2716,
																	trace_out2896);
														} else {
														}
													} else {
													}
												} else {
													if (t_in2716 instanceof org.spoofax.interpreter.terms.IStrategoList) {
														final org.spoofax.interpreter.terms.IStrategoList t98137 = (org.spoofax.interpreter.terms.IStrategoList) t_in2716;
														final org.spoofax.interpreter.terms.IStrategoList lifted_32977 = ds.manual.interpreter.Natives
																.makeNil_1(tf_in2716);
														final I_Strategy lifted_32987 = new Match_1(
																null, ct440);
														final R_default_Value $tmp2564 = lifted_32987
																.exec_default(
																		ic_in2716,
																		senv_in3076,
																		venv_in3166,
																		lifted_32977,
																		tf_in2716,
																		sheap_in3616,
																		vheap_in3256,
																		bool_in2716,
																		trace_in2896);
														final IValue lifted_32997 = $tmp2564.value;
														final ds.manual.interpreter.SState sheap_29553 = $tmp2564
																.get_1();
														final ds.manual.interpreter.VState vheap_28907 = $tmp2564
																.get_2();
														final boolean bool_28468 = $tmp2564
																.get_3();
														final org.spoofax.interpreter.core.StackTracer trace_28531 = $tmp2564
																.get_4();
														final S_1 $tmp2565 = lifted_32997
																.match(S_1.class);
														if ($tmp2565 != null) {
															final org.spoofax.interpreter.terms.IStrategoTerm lifted_30357 = $tmp2565
																	.get_1();
															final I_Strategy lifted_33007 = new Match_1(
																	null,
																	ts5865);
															final R_default_Value $tmp2566 = lifted_33007
																	.exec_default(
																			ic_in2716,
																			senv_in3076,
																			venv_in3166,
																			t98137,
																			tf_in2716,
																			sheap_29553,
																			vheap_28907,
																			bool_28468,
																			trace_28531);
															final IValue lifted_33017 = $tmp2566.value;
															final ds.manual.interpreter.SState sheap_34706 = $tmp2566
																	.get_1();
															final ds.manual.interpreter.VState vheap_34243 = $tmp2566
																	.get_2();
															final boolean bool_33364 = $tmp2566
																	.get_3();
															final org.spoofax.interpreter.core.StackTracer trace_33683 = $tmp2566
																	.get_4();
															final S_1 $tmp2567 = lifted_33017
																	.match(S_1.class);
															if ($tmp2567 != null) {
																final org.spoofax.interpreter.terms.IStrategoTerm lifted_30367 = $tmp2567
																		.get_1();
																final IValue lifted_32967 = new S_1(
																		null,
																		t98137);
																final ds.manual.interpreter.SState sheap_out3616 = sheap_34706;
																final ds.manual.interpreter.VState vheap_out3256 = vheap_34243;
																final boolean bool_out2716 = bool_33364;
																final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_33683;
																final IValue result_out4516 = lifted_32967;
																return new R_ma_Value(
																		result_out4516,
																		sheap_out3616,
																		vheap_out3256,
																		bool_out2716,
																		trace_out2896);
															} else {
															}
														} else {
														}
													} else {
														if (t_in2716 instanceof org.spoofax.interpreter.terms.IStrategoTuple) {
															final org.spoofax.interpreter.terms.IStrategoTuple t98147 = (org.spoofax.interpreter.terms.IStrategoTuple) t_in2716;
															final org.spoofax.interpreter.terms.IStrategoString constr872 = tf_in2716
																	.makeString("");
															final I_Strategy lifted_33057 = new Match_1(
																	null, ct440);
															final R_default_Value $tmp2568 = lifted_33057
																	.exec_default(
																			ic_in2716,
																			senv_in3076,
																			venv_in3166,
																			constr872,
																			tf_in2716,
																			sheap_in3616,
																			vheap_in3256,
																			bool_in2716,
																			trace_in2896);
															final IValue lifted_33067 = $tmp2568.value;
															final ds.manual.interpreter.SState sheap_29554 = $tmp2568
																	.get_1();
															final ds.manual.interpreter.VState vheap_28908 = $tmp2568
																	.get_2();
															final boolean bool_28469 = $tmp2568
																	.get_3();
															final org.spoofax.interpreter.core.StackTracer trace_28532 = $tmp2568
																	.get_4();
															final S_1 $tmp2569 = lifted_33067
																	.match(S_1.class);
															if ($tmp2569 != null) {
																final org.spoofax.interpreter.terms.IStrategoTerm lifted_30377 = $tmp2569
																		.get_1();
																final org.spoofax.interpreter.terms.IStrategoTerm[] lifted_33077 = t98147
																		.getAllSubterms();
																final org.spoofax.interpreter.terms.IStrategoList kids516 = tf_in2716
																		.makeList(lifted_33077);
																final I_Strategy lifted_33087 = new Match_1(
																		null,
																		ts5865);
																final R_default_Value $tmp2570 = lifted_33087
																		.exec_default(
																				ic_in2716,
																				senv_in3076,
																				venv_in3166,
																				kids516,
																				tf_in2716,
																				sheap_29554,
																				vheap_28908,
																				bool_28469,
																				trace_28532);
																final IValue lifted_33097 = $tmp2570.value;
																final ds.manual.interpreter.SState sheap_34707 = $tmp2570
																		.get_1();
																final ds.manual.interpreter.VState vheap_34244 = $tmp2570
																		.get_2();
																final boolean bool_33365 = $tmp2570
																		.get_3();
																final org.spoofax.interpreter.core.StackTracer trace_33684 = $tmp2570
																		.get_4();
																final S_1 $tmp2571 = lifted_33097
																		.match(S_1.class);
																if ($tmp2571 != null) {
																	final org.spoofax.interpreter.terms.IStrategoTerm lifted_30387 = $tmp2571
																			.get_1();
																	final IValue lifted_33037 = new S_1(
																			null,
																			t98147);
																	final ds.manual.interpreter.SState sheap_out3616 = sheap_34707;
																	final ds.manual.interpreter.VState vheap_out3256 = vheap_34244;
																	final boolean bool_out2716 = bool_33365;
																	final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_33684;
																	final IValue result_out4516 = lifted_33037;
																	return new R_ma_Value(
																			result_out4516,
																			sheap_out3616,
																			vheap_out3256,
																			bool_out2716,
																			trace_out2896);
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
									} else {
										final Op_2 $tmp2572 = op860
												.match(Op_2.class);
										if ($tmp2572 != null) {
											final String c1355 = $tmp2572
													.get_1();
											final INodeList<I_STerm> lifted_30447 = $tmp2572
													.get_2();
											if (c1355 != null
													&& c1355.equals("Nil")) {
												if (lifted_30447 != null
														&& lifted_30447
																.equals(NodeList
																		.NIL(Object.class))) {
													final I_Matcher lifted_33127 = new mList_1(
															null, op860);
													final R_ma_Value $tmp2573 = lifted_33127
															.exec_ma(
																	ic_in2716,
																	senv_in3076,
																	venv_in3166,
																	tf_in2716,
																	t_in2716,
																	sheap_in3616,
																	vheap_in3256,
																	bool_in2716,
																	trace_in2896);
													final IValue mv640 = $tmp2573.value;
													final ds.manual.interpreter.SState sheap_29555 = $tmp2573
															.get_1();
													final ds.manual.interpreter.VState vheap_28909 = $tmp2573
															.get_2();
													final boolean bool_28470 = $tmp2573
															.get_3();
													final org.spoofax.interpreter.core.StackTracer trace_28533 = $tmp2573
															.get_4();
													final ds.manual.interpreter.SState sheap_out3616 = sheap_29555;
													final ds.manual.interpreter.VState vheap_out3256 = vheap_28909;
													final boolean bool_out2716 = bool_28470;
													final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_28533;
													final IValue result_out4516 = mv640;
													return new R_ma_Value(
															result_out4516,
															sheap_out3616,
															vheap_out3256,
															bool_out2716,
															trace_out2896);
												} else {
													if (lifted_30447 != null
															&& !lifted_30447
																	.isEmpty()) {
														final I_STerm lifted_30407 = lifted_30447
																.head();
														final INodeList<I_STerm> lifted_30417 = lifted_30447
																.tail();
														final I_Matcher lifted_33167 = new mAppl_1(
																null, op860);
														final R_ma_Value $tmp2574 = lifted_33167
																.exec_ma(
																		ic_in2716,
																		senv_in3076,
																		venv_in3166,
																		tf_in2716,
																		t_in2716,
																		sheap_in3616,
																		vheap_in3256,
																		bool_in2716,
																		trace_in2896);
														final IValue mv641 = $tmp2574.value;
														final ds.manual.interpreter.SState sheap_29556 = $tmp2574
																.get_1();
														final ds.manual.interpreter.VState vheap_28910 = $tmp2574
																.get_2();
														final boolean bool_28471 = $tmp2574
																.get_3();
														final org.spoofax.interpreter.core.StackTracer trace_28534 = $tmp2574
																.get_4();
														final ds.manual.interpreter.SState sheap_out3616 = sheap_29556;
														final ds.manual.interpreter.VState vheap_out3256 = vheap_28910;
														final boolean bool_out2716 = bool_28471;
														final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_28534;
														final IValue result_out4516 = mv641;
														return new R_ma_Value(
																result_out4516,
																sheap_out3616,
																vheap_out3256,
																bool_out2716,
																trace_out2896);
													} else {
													}
												}
											} else {
												if (c1355 != null
														&& c1355.equals("Cons")) {
													if (ds.manual.interpreter.Natives
															.length_1(lifted_30447) == 2) {
														final I_Matcher lifted_33207 = new mList_1(
																null, op860);
														final R_ma_Value $tmp2575 = lifted_33207
																.exec_ma(
																		ic_in2716,
																		senv_in3076,
																		venv_in3166,
																		tf_in2716,
																		t_in2716,
																		sheap_in3616,
																		vheap_in3256,
																		bool_in2716,
																		trace_in2896);
														final IValue mv642 = $tmp2575.value;
														final ds.manual.interpreter.SState sheap_29557 = $tmp2575
																.get_1();
														final ds.manual.interpreter.VState vheap_28911 = $tmp2575
																.get_2();
														final boolean bool_28472 = $tmp2575
																.get_3();
														final org.spoofax.interpreter.core.StackTracer trace_28535 = $tmp2575
																.get_4();
														final ds.manual.interpreter.SState sheap_out3616 = sheap_29557;
														final ds.manual.interpreter.VState vheap_out3256 = vheap_28911;
														final boolean bool_out2716 = bool_28472;
														final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_28535;
														final IValue result_out4516 = mv642;
														return new R_ma_Value(
																result_out4516,
																sheap_out3616,
																vheap_out3256,
																bool_out2716,
																trace_out2896);
													} else {
														if (ds.manual.interpreter.Natives
																.length_1(lifted_30447) == 2) {
														} else {
															final I_Matcher lifted_33237 = new mAppl_1(
																	null, op860);
															final R_ma_Value $tmp2576 = lifted_33237
																	.exec_ma(
																			ic_in2716,
																			senv_in3076,
																			venv_in3166,
																			tf_in2716,
																			t_in2716,
																			sheap_in3616,
																			vheap_in3256,
																			bool_in2716,
																			trace_in2896);
															final IValue mv643 = $tmp2576.value;
															final ds.manual.interpreter.SState sheap_29558 = $tmp2576
																	.get_1();
															final ds.manual.interpreter.VState vheap_28912 = $tmp2576
																	.get_2();
															final boolean bool_28473 = $tmp2576
																	.get_3();
															final org.spoofax.interpreter.core.StackTracer trace_28536 = $tmp2576
																	.get_4();
															final ds.manual.interpreter.SState sheap_out3616 = sheap_29558;
															final ds.manual.interpreter.VState vheap_out3256 = vheap_28912;
															final boolean bool_out2716 = bool_28473;
															final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_28536;
															final IValue result_out4516 = mv643;
															return new R_ma_Value(
																	result_out4516,
																	sheap_out3616,
																	vheap_out3256,
																	bool_out2716,
																	trace_out2896);
														}
													}
												} else {
													if (c1355 != null
															&& c1355.equals("")) {
														if (t_in2716 instanceof org.spoofax.interpreter.terms.IStrategoTuple) {
															final org.spoofax.interpreter.terms.IStrategoTuple t98157 = (org.spoofax.interpreter.terms.IStrategoTuple) t_in2716;
															final org.spoofax.interpreter.terms.IStrategoTerm[] elems160 = t98157
																	.getAllSubterms();
															final org.spoofax.interpreter.terms.IStrategoList lifted_33287 = tf_in2716
																	.makeList(elems160);
															final I_Matcher lifted_33297 = new ml_1(
																	null,
																	lifted_30447);
															final R_ma_Value $tmp2577 = lifted_33297
																	.exec_ma(
																			ic_in2716,
																			senv_in3076,
																			venv_in3166,
																			tf_in2716,
																			lifted_33287,
																			sheap_in3616,
																			vheap_in3256,
																			bool_in2716,
																			trace_in2896);
															final IValue lifted_33307 = $tmp2577.value;
															final ds.manual.interpreter.SState sheap_29559 = $tmp2577
																	.get_1();
															final ds.manual.interpreter.VState vheap_28913 = $tmp2577
																	.get_2();
															final boolean bool_28474 = $tmp2577
																	.get_3();
															final org.spoofax.interpreter.core.StackTracer trace_28537 = $tmp2577
																	.get_4();
															final S_1 $tmp2578 = lifted_33307
																	.match(S_1.class);
															if ($tmp2578 != null) {
																final org.spoofax.interpreter.terms.IStrategoTerm lifted_30427 = $tmp2578
																		.get_1();
																final IValue lifted_33267 = new S_1(
																		null,
																		t98157);
																final ds.manual.interpreter.SState sheap_out3616 = sheap_29559;
																final ds.manual.interpreter.VState vheap_out3256 = vheap_28913;
																final boolean bool_out2716 = bool_28474;
																final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_28537;
																final IValue result_out4516 = lifted_33267;
																return new R_ma_Value(
																		result_out4516,
																		sheap_out3616,
																		vheap_out3256,
																		bool_out2716,
																		trace_out2896);
															} else {
															}
														} else {
														}
													} else {
														if (c1355 != null
																&& c1355.equals("Nil")) {
														} else {
															if (c1355 != null
																	&& c1355.equals("Cons")) {
															} else {
																if (c1355 != null
																		&& c1355.equals("")) {
																} else {
																	final I_Matcher lifted_33337 = new mAppl_1(
																			null,
																			op860);
																	final R_ma_Value $tmp2579 = lifted_33337
																			.exec_ma(
																					ic_in2716,
																					senv_in3076,
																					venv_in3166,
																					tf_in2716,
																					t_in2716,
																					sheap_in3616,
																					vheap_in3256,
																					bool_in2716,
																					trace_in2896);
																	final IValue lifted_33347 = $tmp2579.value;
																	final ds.manual.interpreter.SState sheap_29560 = $tmp2579
																			.get_1();
																	final ds.manual.interpreter.VState vheap_28914 = $tmp2579
																			.get_2();
																	final boolean bool_28475 = $tmp2579
																			.get_3();
																	final org.spoofax.interpreter.core.StackTracer trace_28538 = $tmp2579
																			.get_4();
																	final S_1 $tmp2580 = lifted_33347
																			.match(S_1.class);
																	if ($tmp2580 != null) {
																		final org.spoofax.interpreter.terms.IStrategoTerm lifted_30437 = $tmp2580
																				.get_1();
																		final IValue lifted_33327 = new S_1(
																				null,
																				t_in2716);
																		final ds.manual.interpreter.SState sheap_out3616 = sheap_29560;
																		final ds.manual.interpreter.VState vheap_out3256 = vheap_28914;
																		final boolean bool_out2716 = bool_28475;
																		final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_28538;
																		final IValue result_out4516 = lifted_33327;
																		return new R_ma_Value(
																				result_out4516,
																				sheap_out3616,
																				vheap_out3256,
																				bool_out2716,
																				trace_out2896);
																	} else {
																	}
																}
															}
														}
													}
												}
											}
										} else {
										}
									}
								}
							}
						}
					}
				}
			}
		}
		{
			final IValue lifted_33107 = new F_0(null);
			final ds.manual.interpreter.SState sheap_out3616 = sheap_in3616;
			final ds.manual.interpreter.VState vheap_out3256 = vheap_in3256;
			final boolean bool_out2716 = bool_in2716;
			final org.spoofax.interpreter.core.StackTracer trace_out2896 = trace_in2896;
			final IValue result_out4516 = lifted_33107;
			return new R_ma_Value(result_out4516, sheap_out3616, vheap_out3256,
					bool_out2716, trace_out2896);
		}
	}

	public I_STerm get_1() {
		return this._1;
	}
}
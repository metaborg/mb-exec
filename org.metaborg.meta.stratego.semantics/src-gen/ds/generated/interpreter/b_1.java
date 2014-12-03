package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class b_1 extends AbstractNode implements I_Builder {
	private boolean hasSpecialized;

	@Child
	public I_PreTerm _1;

	public b_1(INodeSource source, I_PreTerm _1) {
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
		final b_1 other = (b_1) obj;
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

	public R_bld_BuildRes exec_bld(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			org.spoofax.interpreter.terms.IStrategoTerm _3,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _4,
			org.spoofax.interpreter.terms.ITermFactory _5,
			ds.manual.interpreter.SState _6, ds.manual.interpreter.VState _7,
			boolean _8, org.spoofax.interpreter.core.StackTracer _9) {
		this.specializeChildren(0);
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2720 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3080 = _2;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2720 = _3;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3170 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2720 = _5;
		final ds.manual.interpreter.SState sheap_in3620 = _6;
		final ds.manual.interpreter.VState vheap_in3260 = _7;
		final boolean bool_in2720 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2900 = _9;
		final I_PreTerm lifted_35187 = this._1;
		{
			final Var_1 $tmp2593 = lifted_35187.match(Var_1.class);
			if ($tmp2593 != null) {
				final String x3751 = $tmp2593.get_1();
				final I_VHeapOp lifted_34587 = new VLookup_2(null, venv_in3170,
						x3751);
				final R_vlook_VLookupResult $tmp2594 = lifted_34587
						.exec_vlook(vheap_in3260);
				final I_VLookupResult lifted_34597 = $tmp2594.value;
				final ds.manual.interpreter.VState vheap_28918 = $tmp2594
						.get_1();
				final VLookupResult_2 $tmp2595 = lifted_34597
						.match(VLookupResult_2.class);
				if ($tmp2595 != null) {
					final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> lifted_30697 = $tmp2595
							.get_1();
					final IValue lifted_34607 = $tmp2595.get_2();
					final S_1 $tmp2596 = lifted_34607.match(S_1.class);
					if ($tmp2596 != null) {
						final org.spoofax.interpreter.terms.IStrategoTerm t10087 = $tmp2596
								.get_1();
						final I_BuildRes lifted_34577 = new BS_1(null, t10087);
						final ds.manual.interpreter.SState sheap_out3620 = sheap_in3620;
						final ds.manual.interpreter.VState vheap_out3260 = vheap_28918;
						final boolean bool_out2720 = bool_in2720;
						final org.spoofax.interpreter.core.StackTracer trace_out2900 = trace_in2900;
						final I_BuildRes result_out4520 = lifted_34577;
						return new R_bld_BuildRes(result_out4520,
								sheap_out3620, vheap_out3260, bool_out2720,
								trace_out2900);
					} else {
					}
				} else {
				}
			} else {
				final Int_1 $tmp2597 = lifted_35187.match(Int_1.class);
				if ($tmp2597 != null) {
					final String stringI356 = $tmp2597.get_1();
					final int i_21 = ds.manual.interpreter.Natives
							.parseInt_1(stringI356);
					final org.spoofax.interpreter.terms.IStrategoInt aint195 = tf_in2720
							.makeInt(i_21);
					final I_BuildRes lifted_34627 = new BS_1(null, aint195);
					final ds.manual.interpreter.SState sheap_out3620 = sheap_in3620;
					final ds.manual.interpreter.VState vheap_out3260 = vheap_in3260;
					final boolean bool_out2720 = bool_in2720;
					final org.spoofax.interpreter.core.StackTracer trace_out2900 = trace_in2900;
					final I_BuildRes result_out4520 = lifted_34627;
					return new R_bld_BuildRes(result_out4520, sheap_out3620,
							vheap_out3260, bool_out2720, trace_out2900);
				} else {
					final Real_1 $tmp2598 = lifted_35187.match(Real_1.class);
					if ($tmp2598 != null) {
						final String stringR356 = $tmp2598.get_1();
						final double r_21 = ds.manual.interpreter.Natives
								.parseReal_1(stringR356);
						final org.spoofax.interpreter.terms.IStrategoReal areal195 = tf_in2720
								.makeReal(r_21);
						final I_BuildRes lifted_34647 = new BS_1(null, areal195);
						final ds.manual.interpreter.SState sheap_out3620 = sheap_in3620;
						final ds.manual.interpreter.VState vheap_out3260 = vheap_in3260;
						final boolean bool_out2720 = bool_in2720;
						final org.spoofax.interpreter.core.StackTracer trace_out2900 = trace_in2900;
						final I_BuildRes result_out4520 = lifted_34647;
						return new R_bld_BuildRes(result_out4520,
								sheap_out3620, vheap_out3260, bool_out2720,
								trace_out2900);
					} else {
						final Str_1 $tmp2599 = lifted_35187.match(Str_1.class);
						if ($tmp2599 != null) {
							final String s11435 = $tmp2599.get_1();
							final org.spoofax.interpreter.terms.IStrategoString astr195 = tf_in2720
									.makeString(s11435);
							final I_BuildRes lifted_34667 = new BS_1(null,
									astr195);
							final ds.manual.interpreter.SState sheap_out3620 = sheap_in3620;
							final ds.manual.interpreter.VState vheap_out3260 = vheap_in3260;
							final boolean bool_out2720 = bool_in2720;
							final org.spoofax.interpreter.core.StackTracer trace_out2900 = trace_in2900;
							final I_BuildRes result_out4520 = lifted_34667;
							return new R_bld_BuildRes(result_out4520,
									sheap_out3620, vheap_out3260, bool_out2720,
									trace_out2900);
						} else {
							final Explode_2 $tmp2600 = lifted_35187
									.match(Explode_2.class);
							if ($tmp2600 != null) {
								final I_STerm cname_expr615 = $tmp2600.get_1();
								final I_STerm ts_expr615 = $tmp2600.get_2();
								final I_Strategy lifted_34947 = new Build_1(
										null, cname_expr615);
								final R_default_Value $tmp2601 = lifted_34947
										.exec_default(ic_in2720, senv_in3080,
												venv_in3170, t_in2720,
												tf_in2720, sheap_in3620,
												vheap_in3260, bool_in2720,
												trace_in2900);
								final IValue lifted_34957 = $tmp2601.value;
								final ds.manual.interpreter.SState sheap_29564 = $tmp2601
										.get_1();
								final ds.manual.interpreter.VState vheap_28919 = $tmp2601
										.get_2();
								final boolean bool_28479 = $tmp2601.get_3();
								final org.spoofax.interpreter.core.StackTracer trace_28542 = $tmp2601
										.get_4();
								final S_1 $tmp2602 = lifted_34957
										.match(S_1.class);
								if ($tmp2602 != null) {
									final org.spoofax.interpreter.terms.IStrategoTerm c1357 = $tmp2602
											.get_1();
									final I_Strategy lifted_34967 = new Build_1(
											null, ts_expr615);
									final R_default_Value $tmp2603 = lifted_34967
											.exec_default(ic_in2720,
													senv_in3080, venv_in3170,
													t_in2720, tf_in2720,
													sheap_29564, vheap_28919,
													bool_28479, trace_28542);
									final IValue lifted_34977 = $tmp2603.value;
									final ds.manual.interpreter.SState sheap_34710 = $tmp2603
											.get_1();
									final ds.manual.interpreter.VState vheap_34247 = $tmp2603
											.get_2();
									final boolean bool_33368 = $tmp2603.get_3();
									final org.spoofax.interpreter.core.StackTracer trace_33687 = $tmp2603
											.get_4();
									final S_1 $tmp2604 = lifted_34977
											.match(S_1.class);
									if ($tmp2604 != null) {
										final org.spoofax.interpreter.terms.IStrategoTerm ts5866 = $tmp2604
												.get_1();
										if (ts5866 instanceof org.spoofax.interpreter.terms.IStrategoList) {
											final org.spoofax.interpreter.terms.IStrategoList ts56377 = (org.spoofax.interpreter.terms.IStrategoList) ts5866;
											if (c1357 instanceof org.spoofax.interpreter.terms.IStrategoInt) {
												final org.spoofax.interpreter.terms.IStrategoInt c12787 = (org.spoofax.interpreter.terms.IStrategoInt) c1357;
												final I_BuildRes lifted_34687 = new BS_1(
														null, c12787);
												final ds.manual.interpreter.SState sheap_out3620 = sheap_34710;
												final ds.manual.interpreter.VState vheap_out3260 = vheap_34247;
												final boolean bool_out2720 = bool_33368;
												final org.spoofax.interpreter.core.StackTracer trace_out2900 = trace_33687;
												final I_BuildRes result_out4520 = lifted_34687;
												return new R_bld_BuildRes(
														result_out4520,
														sheap_out3620,
														vheap_out3260,
														bool_out2720,
														trace_out2900);
											} else {
												if (c1357 instanceof org.spoofax.interpreter.terms.IStrategoList) {
													final org.spoofax.interpreter.terms.IStrategoList c12797 = (org.spoofax.interpreter.terms.IStrategoList) c1357;
													final I_BuildRes lifted_34747 = new BS_1(
															null, ts56377);
													final ds.manual.interpreter.SState sheap_out3620 = sheap_34710;
													final ds.manual.interpreter.VState vheap_out3260 = vheap_34247;
													final boolean bool_out2720 = bool_33368;
													final org.spoofax.interpreter.core.StackTracer trace_out2900 = trace_33687;
													final I_BuildRes result_out4520 = lifted_34747;
													return new R_bld_BuildRes(
															result_out4520,
															sheap_out3620,
															vheap_out3260,
															bool_out2720,
															trace_out2900);
												} else {
													if (c1357 instanceof org.spoofax.interpreter.terms.IStrategoString) {
														final org.spoofax.interpreter.terms.IStrategoString c12827 = (org.spoofax.interpreter.terms.IStrategoString) c1357;
														final String cname405 = c12827
																.stringValue();
														if (cname405 != null
																&& cname405
																		.equals("")) {
															final org.spoofax.interpreter.terms.IStrategoTerm[] lifted_34857 = ts56377
																	.getAllSubterms();
															final org.spoofax.interpreter.terms.IStrategoTuple tupl195 = tf_in2720
																	.makeTuple(lifted_34857);
															final I_BuildRes lifted_34807 = new BS_1(
																	null,
																	tupl195);
															final ds.manual.interpreter.SState sheap_out3620 = sheap_34710;
															final ds.manual.interpreter.VState vheap_out3260 = vheap_34247;
															final boolean bool_out2720 = bool_33368;
															final org.spoofax.interpreter.core.StackTracer trace_out2900 = trace_33687;
															final I_BuildRes result_out4520 = lifted_34807;
															return new R_bld_BuildRes(
																	result_out4520,
																	sheap_out3620,
																	vheap_out3260,
																	bool_out2720,
																	trace_out2900);
														} else {
															if (ds.manual.interpreter.Natives
																	.isQuotedString_1(cname405) == true) {
																final String svalue195 = ds.manual.interpreter.Natives
																		.unquoteString_1(cname405);
																final org.spoofax.interpreter.terms.IStrategoString s11436 = tf_in2720
																		.makeString(svalue195);
																final I_BuildRes lifted_34877 = new BS_1(
																		null,
																		s11436);
																final ds.manual.interpreter.SState sheap_out3620 = sheap_34710;
																final ds.manual.interpreter.VState vheap_out3260 = vheap_34247;
																final boolean bool_out2720 = bool_33368;
																final org.spoofax.interpreter.core.StackTracer trace_out2900 = trace_33687;
																final I_BuildRes result_out4520 = lifted_34877;
																return new R_bld_BuildRes(
																		result_out4520,
																		sheap_out3620,
																		vheap_out3260,
																		bool_out2720,
																		trace_out2900);
															} else {
																if (ds.manual.interpreter.Natives
																		.isQuotedString_1(cname405) == false) {
																	final int lifted_34987 = ts56377
																			.size();
																	final org.spoofax.interpreter.terms.IStrategoConstructor constr873 = tf_in2720
																			.makeConstructor(
																					cname405,
																					lifted_34987);
																	final org.spoofax.interpreter.terms.IStrategoTerm[] kids517 = ts56377
																			.getAllSubterms();
																	final org.spoofax.interpreter.terms.IStrategoAppl appl390 = tf_in2720
																			.makeAppl(
																					constr873,
																					kids517);
																	final I_BuildRes lifted_34937 = new BS_1(
																			null,
																			appl390);
																	final ds.manual.interpreter.SState sheap_out3620 = sheap_34710;
																	final ds.manual.interpreter.VState vheap_out3260 = vheap_34247;
																	final boolean bool_out2720 = bool_33368;
																	final org.spoofax.interpreter.core.StackTracer trace_out2900 = trace_33687;
																	final I_BuildRes result_out4520 = lifted_34937;
																	return new R_bld_BuildRes(
																			result_out4520,
																			sheap_out3620,
																			vheap_out3260,
																			bool_out2720,
																			trace_out2900);
																} else {
																}
															}
														}
													} else {
													}
												}
											}
										} else {
										}
									} else {
									}
								} else {
								}
							} else {
								final Op_2 $tmp2605 = lifted_35187
										.match(Op_2.class);
								if ($tmp2605 != null) {
									final String c1358 = $tmp2605.get_1();
									final INodeList<I_STerm> ts5867 = $tmp2605
											.get_2();
									if (c1358 != null && c1358.equals("Nil")) {
										if (ds.manual.interpreter.Natives
												.length_1(ts5867) == 0) {
											final I_Builder lifted_35017 = new bList_1(
													null, lifted_35187);
											final R_bld_BuildRes $tmp2606 = lifted_35017
													.exec_bld(ic_in2720,
															senv_in3080,
															t_in2720,
															venv_in3170,
															tf_in2720,
															sheap_in3620,
															vheap_in3260,
															bool_in2720,
															trace_in2900);
											final I_BuildRes bv975 = $tmp2606.value;
											final ds.manual.interpreter.SState sheap_29565 = $tmp2606
													.get_1();
											final ds.manual.interpreter.VState vheap_28920 = $tmp2606
													.get_2();
											final boolean bool_28480 = $tmp2606
													.get_3();
											final org.spoofax.interpreter.core.StackTracer trace_28543 = $tmp2606
													.get_4();
											final ds.manual.interpreter.SState sheap_out3620 = sheap_29565;
											final ds.manual.interpreter.VState vheap_out3260 = vheap_28920;
											final boolean bool_out2720 = bool_28480;
											final org.spoofax.interpreter.core.StackTracer trace_out2900 = trace_28543;
											final I_BuildRes result_out4520 = bv975;
											return new R_bld_BuildRes(
													result_out4520,
													sheap_out3620,
													vheap_out3260,
													bool_out2720, trace_out2900);
										} else {
											if (ds.manual.interpreter.Natives
													.length_1(ts5867) == 0) {
											} else {
												final I_Builder lifted_35047 = new bAppl_1(
														null, lifted_35187);
												final R_bld_BuildRes $tmp2607 = lifted_35047
														.exec_bld(ic_in2720,
																senv_in3080,
																t_in2720,
																venv_in3170,
																tf_in2720,
																sheap_in3620,
																vheap_in3260,
																bool_in2720,
																trace_in2900);
												final I_BuildRes bv976 = $tmp2607.value;
												final ds.manual.interpreter.SState sheap_29566 = $tmp2607
														.get_1();
												final ds.manual.interpreter.VState vheap_28921 = $tmp2607
														.get_2();
												final boolean bool_28481 = $tmp2607
														.get_3();
												final org.spoofax.interpreter.core.StackTracer trace_28544 = $tmp2607
														.get_4();
												final ds.manual.interpreter.SState sheap_out3620 = sheap_29566;
												final ds.manual.interpreter.VState vheap_out3260 = vheap_28921;
												final boolean bool_out2720 = bool_28481;
												final org.spoofax.interpreter.core.StackTracer trace_out2900 = trace_28544;
												final I_BuildRes result_out4520 = bv976;
												return new R_bld_BuildRes(
														result_out4520,
														sheap_out3620,
														vheap_out3260,
														bool_out2720,
														trace_out2900);
											}
										}
									} else {
										if (c1358 != null
												&& c1358.equals("Cons")) {
											if (ds.manual.interpreter.Natives
													.length_1(ts5867) == 2) {
												final I_Builder lifted_35077 = new bList_1(
														null, lifted_35187);
												final R_bld_BuildRes $tmp2608 = lifted_35077
														.exec_bld(ic_in2720,
																senv_in3080,
																t_in2720,
																venv_in3170,
																tf_in2720,
																sheap_in3620,
																vheap_in3260,
																bool_in2720,
																trace_in2900);
												final I_BuildRes bv977 = $tmp2608.value;
												final ds.manual.interpreter.SState sheap_29567 = $tmp2608
														.get_1();
												final ds.manual.interpreter.VState vheap_28922 = $tmp2608
														.get_2();
												final boolean bool_28482 = $tmp2608
														.get_3();
												final org.spoofax.interpreter.core.StackTracer trace_28545 = $tmp2608
														.get_4();
												final ds.manual.interpreter.SState sheap_out3620 = sheap_29567;
												final ds.manual.interpreter.VState vheap_out3260 = vheap_28922;
												final boolean bool_out2720 = bool_28482;
												final org.spoofax.interpreter.core.StackTracer trace_out2900 = trace_28545;
												final I_BuildRes result_out4520 = bv977;
												return new R_bld_BuildRes(
														result_out4520,
														sheap_out3620,
														vheap_out3260,
														bool_out2720,
														trace_out2900);
											} else {
												if (ds.manual.interpreter.Natives
														.length_1(ts5867) == 2) {
												} else {
													final I_Builder lifted_35107 = new bAppl_1(
															null, lifted_35187);
													final R_bld_BuildRes $tmp2609 = lifted_35107
															.exec_bld(
																	ic_in2720,
																	senv_in3080,
																	t_in2720,
																	venv_in3170,
																	tf_in2720,
																	sheap_in3620,
																	vheap_in3260,
																	bool_in2720,
																	trace_in2900);
													final I_BuildRes bv978 = $tmp2609.value;
													final ds.manual.interpreter.SState sheap_29568 = $tmp2609
															.get_1();
													final ds.manual.interpreter.VState vheap_28923 = $tmp2609
															.get_2();
													final boolean bool_28483 = $tmp2609
															.get_3();
													final org.spoofax.interpreter.core.StackTracer trace_28546 = $tmp2609
															.get_4();
													final ds.manual.interpreter.SState sheap_out3620 = sheap_29568;
													final ds.manual.interpreter.VState vheap_out3260 = vheap_28923;
													final boolean bool_out2720 = bool_28483;
													final org.spoofax.interpreter.core.StackTracer trace_out2900 = trace_28546;
													final I_BuildRes result_out4520 = bv978;
													return new R_bld_BuildRes(
															result_out4520,
															sheap_out3620,
															vheap_out3260,
															bool_out2720,
															trace_out2900);
												}
											}
										} else {
											if (c1358 != null
													&& c1358.equals("")) {
												final I_Builder lifted_35157 = new bl_1(
														null, ts5867);
												final R_blds_BuildRes $tmp2610 = lifted_35157
														.exec_blds(ic_in2720,
																senv_in3080,
																venv_in3170,
																t_in2720,
																tf_in2720,
																sheap_in3620,
																vheap_in3260,
																bool_in2720,
																trace_in2900);
												final I_BuildRes lifted_35167 = $tmp2610.value;
												final ds.manual.interpreter.SState sheap_29569 = $tmp2610
														.get_1();
												final ds.manual.interpreter.VState vheap_28924 = $tmp2610
														.get_2();
												final boolean bool_28484 = $tmp2610
														.get_3();
												final org.spoofax.interpreter.core.StackTracer trace_28547 = $tmp2610
														.get_4();
												final BSS_1 $tmp2611 = lifted_35167
														.match(BSS_1.class);
												if ($tmp2611 != null) {
													final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> ts_103 = $tmp2611
															.get_1();
													final org.spoofax.interpreter.terms.IStrategoTerm[] ts__30 = ds.manual.interpreter.Natives
															.List2TARRAY_1(ts_103);
													final org.spoofax.interpreter.terms.IStrategoTuple lifted_35177 = tf_in2720
															.makeTuple(ts__30);
													final I_BuildRes lifted_35137 = new BS_1(
															null, lifted_35177);
													final ds.manual.interpreter.SState sheap_out3620 = sheap_29569;
													final ds.manual.interpreter.VState vheap_out3260 = vheap_28924;
													final boolean bool_out2720 = bool_28484;
													final org.spoofax.interpreter.core.StackTracer trace_out2900 = trace_28547;
													final I_BuildRes result_out4520 = lifted_35137;
													return new R_bld_BuildRes(
															result_out4520,
															sheap_out3620,
															vheap_out3260,
															bool_out2720,
															trace_out2900);
												} else {
												}
											} else {
												if (c1358 != null
														&& c1358.equals("Cons")) {
												} else {
													if (c1358 != null
															&& c1358.equals("Nil")) {
													} else {
														if (c1358 != null
																&& c1358.equals("")) {
														} else {
															final I_PreTerm lifted_35207 = new Op_2(
																	null,
																	c1358,
																	ts5867);
															final I_Builder lifted_35197 = new bAppl_1(
																	null,
																	lifted_35207);
															final R_bld_BuildRes $tmp2612 = lifted_35197
																	.exec_bld(
																			ic_in2720,
																			senv_in3080,
																			t_in2720,
																			venv_in3170,
																			tf_in2720,
																			sheap_in3620,
																			vheap_in3260,
																			bool_in2720,
																			trace_in2900);
															final I_BuildRes bv979 = $tmp2612.value;
															final ds.manual.interpreter.SState sheap_29570 = $tmp2612
																	.get_1();
															final ds.manual.interpreter.VState vheap_28925 = $tmp2612
																	.get_2();
															final boolean bool_28485 = $tmp2612
																	.get_3();
															final org.spoofax.interpreter.core.StackTracer trace_28548 = $tmp2612
																	.get_4();
															final ds.manual.interpreter.SState sheap_out3620 = sheap_29570;
															final ds.manual.interpreter.VState vheap_out3260 = vheap_28925;
															final boolean bool_out2720 = bool_28485;
															final org.spoofax.interpreter.core.StackTracer trace_out2900 = trace_28548;
															final I_BuildRes result_out4520 = bv979;
															return new R_bld_BuildRes(
																	result_out4520,
																	sheap_out3620,
																	vheap_out3260,
																	bool_out2720,
																	trace_out2900);
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
		{
			final I_BuildRes lifted_34997 = new BF_0(null);
			final ds.manual.interpreter.SState sheap_out3620 = sheap_in3620;
			final ds.manual.interpreter.VState vheap_out3260 = vheap_in3260;
			final boolean bool_out2720 = bool_in2720;
			final org.spoofax.interpreter.core.StackTracer trace_out2900 = trace_in2900;
			final I_BuildRes result_out4520 = lifted_34997;
			return new R_bld_BuildRes(result_out4520, sheap_out3620,
					vheap_out3260, bool_out2720, trace_out2900);
		}
	}

	public R_blds_BuildRes exec_blds(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _3,
			org.spoofax.interpreter.terms.IStrategoTerm _4,
			org.spoofax.interpreter.terms.ITermFactory _5,
			ds.manual.interpreter.SState _6, ds.manual.interpreter.VState _7,
			boolean _8, org.spoofax.interpreter.core.StackTracer _9) {
		this.specializeChildren(0);
		throw new InterpreterException("Rule failure");
	}

	public I_PreTerm get_1() {
		return this._1;
	}
}
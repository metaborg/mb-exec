package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class mList_1 extends AbstractNode implements I_Matcher {
	private boolean hasSpecialized;

	@Child
	public I_STerm _1;

	public mList_1(INodeSource source, I_STerm _1) {
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
		final mList_1 other = (mList_1) obj;
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2719 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3079 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3169 = _3;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2719 = _4;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2719 = _5;
		final ds.manual.interpreter.SState sheap_in3619 = _6;
		final ds.manual.interpreter.VState vheap_in3259 = _7;
		final boolean bool_in2719 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2899 = _9;
		final I_STerm lifted_31667 = this._1;
		{
			final Op_2 $tmp2588 = lifted_31667.match(Op_2.class);
			if ($tmp2588 != null) {
				final String lifted_29987 = $tmp2588.get_1();
				final INodeList<I_STerm> lifted_31687 = $tmp2588.get_2();
				if (lifted_31687 != null
						&& lifted_31687.equals(NodeList.NIL(Object.class))) {
					if (t_in2719 instanceof org.spoofax.interpreter.terms.IStrategoList) {
						final org.spoofax.interpreter.terms.IStrategoList t98007 = (org.spoofax.interpreter.terms.IStrategoList) t_in2719;
						if (t98007.isEmpty() == true) {
							final IValue lifted_31647 = new S_1(null, t98007);
							final ds.manual.interpreter.SState sheap_out3619 = sheap_in3619;
							final ds.manual.interpreter.VState vheap_out3259 = vheap_in3259;
							final boolean bool_out2719 = bool_in2719;
							final org.spoofax.interpreter.core.StackTracer trace_out2899 = trace_in2899;
							final IValue result_out4519 = lifted_31647;
							return new R_ma_Value(result_out4519,
									sheap_out3619, vheap_out3259, bool_out2719,
									trace_out2899);
						} else {
						}
					} else {
					}
				} else {
					if (lifted_31687 != null && !lifted_31687.isEmpty()) {
						final I_STerm p1184 = lifted_31687.head();
						final INodeList<I_STerm> lifted_31757 = lifted_31687
								.tail();
						if (lifted_31757 != null && !lifted_31757.isEmpty()) {
							final I_STerm p2165 = lifted_31757.head();
							final INodeList<I_STerm> lifted_31767 = lifted_31757
									.tail();
							if (lifted_31767 != null
									&& lifted_31767.equals(NodeList
											.NIL(Object.class))) {
								if (t_in2719 instanceof org.spoofax.interpreter.terms.IStrategoList) {
									final org.spoofax.interpreter.terms.IStrategoList t98017 = (org.spoofax.interpreter.terms.IStrategoList) t_in2719;
									if (t98017.isEmpty() == false) {
										final org.spoofax.interpreter.terms.IStrategoTerm lifted_31697 = t98017
												.head();
										final I_Strategy lifted_31707 = new Match_1(
												null, p1184);
										final R_default_Value $tmp2589 = lifted_31707
												.exec_default(ic_in2719,
														senv_in3079,
														venv_in3169,
														lifted_31697,
														tf_in2719,
														sheap_in3619,
														vheap_in3259,
														bool_in2719,
														trace_in2899);
										final IValue lifted_31717 = $tmp2589.value;
										final ds.manual.interpreter.SState sheap_29563 = $tmp2589
												.get_1();
										final ds.manual.interpreter.VState vheap_28917 = $tmp2589
												.get_2();
										final boolean bool_28478 = $tmp2589
												.get_3();
										final org.spoofax.interpreter.core.StackTracer trace_28541 = $tmp2589
												.get_4();
										final S_1 $tmp2590 = lifted_31717
												.match(S_1.class);
										if ($tmp2590 != null) {
											final org.spoofax.interpreter.terms.IStrategoTerm lifted_29967 = $tmp2590
													.get_1();
											final org.spoofax.interpreter.terms.IStrategoList lifted_31727 = t98017
													.tail();
											final I_Strategy lifted_31737 = new Match_1(
													null, p2165);
											final R_default_Value $tmp2591 = lifted_31737
													.exec_default(ic_in2719,
															senv_in3079,
															venv_in3169,
															lifted_31727,
															tf_in2719,
															sheap_29563,
															vheap_28917,
															bool_28478,
															trace_28541);
											final IValue lifted_31747 = $tmp2591.value;
											final ds.manual.interpreter.SState sheap_34709 = $tmp2591
													.get_1();
											final ds.manual.interpreter.VState vheap_34246 = $tmp2591
													.get_2();
											final boolean bool_33367 = $tmp2591
													.get_3();
											final org.spoofax.interpreter.core.StackTracer trace_33686 = $tmp2591
													.get_4();
											final S_1 $tmp2592 = lifted_31747
													.match(S_1.class);
											if ($tmp2592 != null) {
												final org.spoofax.interpreter.terms.IStrategoTerm lifted_29977 = $tmp2592
														.get_1();
												final IValue lifted_31677 = new S_1(
														null, t98017);
												final ds.manual.interpreter.SState sheap_out3619 = sheap_34709;
												final ds.manual.interpreter.VState vheap_out3259 = vheap_34246;
												final boolean bool_out2719 = bool_33367;
												final org.spoofax.interpreter.core.StackTracer trace_out2899 = trace_33686;
												final IValue result_out4519 = lifted_31677;
												return new R_ma_Value(
														result_out4519,
														sheap_out3619,
														vheap_out3259,
														bool_out2719,
														trace_out2899);
											} else {
											}
										} else {
										}
									} else {
									}
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
		}
		{
			final IValue lifted_31777 = new F_0(null);
			final ds.manual.interpreter.SState sheap_out3619 = sheap_in3619;
			final ds.manual.interpreter.VState vheap_out3259 = vheap_in3259;
			final boolean bool_out2719 = bool_in2719;
			final org.spoofax.interpreter.core.StackTracer trace_out2899 = trace_in2899;
			final IValue result_out4519 = lifted_31777;
			return new R_ma_Value(result_out4519, sheap_out3619, vheap_out3259,
					bool_out2719, trace_out2899);
		}
	}

	public I_STerm get_1() {
		return this._1;
	}
}
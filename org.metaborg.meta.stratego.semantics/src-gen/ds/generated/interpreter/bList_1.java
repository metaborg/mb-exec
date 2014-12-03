package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class bList_1 extends AbstractNode implements I_Builder {
	private boolean hasSpecialized;

	@Child
	public I_PreTerm _1;

	public bList_1(INodeSource source, I_PreTerm _1) {
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
		final bList_1 other = (bList_1) obj;
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2723 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3083 = _2;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2723 = _3;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3173 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2723 = _5;
		final ds.manual.interpreter.SState sheap_in3623 = _6;
		final ds.manual.interpreter.VState vheap_in3263 = _7;
		final boolean bool_in2723 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2903 = _9;
		final I_PreTerm lifted_33887 = this._1;
		{
			final Op_2 $tmp2620 = lifted_33887.match(Op_2.class);
			if ($tmp2620 != null) {
				final String lifted_30597 = $tmp2620.get_1();
				final INodeList<I_STerm> lifted_33907 = $tmp2620.get_2();
				if (lifted_33907 != null
						&& lifted_33907.equals(NodeList.NIL(Object.class))) {
					final org.spoofax.interpreter.terms.IStrategoList nil195 = ds.manual.interpreter.Natives
							.makeNil_1(tf_in2723);
					final I_BuildRes lifted_33867 = new BS_1(null, nil195);
					final ds.manual.interpreter.SState sheap_out3623 = sheap_in3623;
					final ds.manual.interpreter.VState vheap_out3263 = vheap_in3263;
					final boolean bool_out2723 = bool_in2723;
					final org.spoofax.interpreter.core.StackTracer trace_out2903 = trace_in2903;
					final I_BuildRes result_out4523 = lifted_33867;
					return new R_bld_BuildRes(result_out4523, sheap_out3623,
							vheap_out3263, bool_out2723, trace_out2903);
				} else {
					if (lifted_33907 != null && !lifted_33907.isEmpty()) {
						final I_STerm t1196 = lifted_33907.head();
						final INodeList<I_STerm> lifted_33967 = lifted_33907
								.tail();
						if (lifted_33967 != null && !lifted_33967.isEmpty()) {
							final I_STerm t2195 = lifted_33967.head();
							final INodeList<I_STerm> lifted_33977 = lifted_33967
									.tail();
							if (lifted_33977 != null
									&& lifted_33977.equals(NodeList
											.NIL(Object.class))) {
								final I_Strategy lifted_33917 = new Build_1(
										null, t1196);
								final R_default_Value $tmp2621 = lifted_33917
										.exec_default(ic_in2723, senv_in3083,
												venv_in3173, t_in2723,
												tf_in2723, sheap_in3623,
												vheap_in3263, bool_in2723,
												trace_in2903);
								final IValue lifted_33927 = $tmp2621.value;
								final ds.manual.interpreter.SState sheap_29573 = $tmp2621
										.get_1();
								final ds.manual.interpreter.VState vheap_28928 = $tmp2621
										.get_2();
								final boolean bool_28488 = $tmp2621.get_3();
								final org.spoofax.interpreter.core.StackTracer trace_28551 = $tmp2621
										.get_4();
								final S_1 $tmp2622 = lifted_33927
										.match(S_1.class);
								if ($tmp2622 != null) {
									final org.spoofax.interpreter.terms.IStrategoTerm head672 = $tmp2622
											.get_1();
									final I_Strategy lifted_33937 = new Build_1(
											null, t2195);
									final R_default_Value $tmp2623 = lifted_33937
											.exec_default(ic_in2723,
													senv_in3083, venv_in3173,
													t_in2723, tf_in2723,
													sheap_29573, vheap_28928,
													bool_28488, trace_28551);
									final IValue lifted_33947 = $tmp2623.value;
									final ds.manual.interpreter.SState sheap_34712 = $tmp2623
											.get_1();
									final ds.manual.interpreter.VState vheap_34249 = $tmp2623
											.get_2();
									final boolean bool_33370 = $tmp2623.get_3();
									final org.spoofax.interpreter.core.StackTracer trace_33689 = $tmp2623
											.get_4();
									final S_1 $tmp2624 = lifted_33947
											.match(S_1.class);
									if ($tmp2624 != null) {
										final org.spoofax.interpreter.terms.IStrategoTerm tail496 = $tmp2624
												.get_1();
										if (tail496 instanceof org.spoofax.interpreter.terms.IStrategoList) {
											final org.spoofax.interpreter.terms.IStrategoList tail4837 = (org.spoofax.interpreter.terms.IStrategoList) tail496;
											final org.spoofax.interpreter.terms.IStrategoList lifted_33957 = tf_in2723
													.makeListCons(head672,
															tail4837);
											final I_BuildRes lifted_33897 = new BS_1(
													null, lifted_33957);
											final ds.manual.interpreter.SState sheap_out3623 = sheap_34712;
											final ds.manual.interpreter.VState vheap_out3263 = vheap_34249;
											final boolean bool_out2723 = bool_33370;
											final org.spoofax.interpreter.core.StackTracer trace_out2903 = trace_33689;
											final I_BuildRes result_out4523 = lifted_33897;
											return new R_bld_BuildRes(
													result_out4523,
													sheap_out3623,
													vheap_out3263,
													bool_out2723, trace_out2903);
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
			throw new InterpreterException("Rule failure");
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
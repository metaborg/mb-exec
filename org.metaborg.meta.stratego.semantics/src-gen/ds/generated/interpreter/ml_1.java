package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class ml_1 extends AbstractNode implements I_Matcher {
	private boolean hasSpecialized;

	@Children
	public INodeList<I_STerm> _1;

	public ml_1(INodeSource source, INodeList<I_STerm> _1) {
		this.setSourceInfo(source);
		this._1 = adoptChildren(_1);
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
		final ml_1 other = (ml_1) obj;
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
			for (I_STerm _1_elem : _1) {
				if (_1_elem instanceof IGenericNode) {
					((IGenericNode) _1_elem).specialize(depth);
				}
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2717 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3077 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3167 = _3;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2717 = _4;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2717 = _5;
		final ds.manual.interpreter.SState sheap_in3617 = _6;
		final ds.manual.interpreter.VState vheap_in3257 = _7;
		final boolean bool_in2717 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2897 = _9;
		final INodeList<I_STerm> lifted_31807 = this._1;
		{
			if (lifted_31807 != null
					&& lifted_31807.equals(NodeList.NIL(Object.class))) {
				if (t_in2717 instanceof org.spoofax.interpreter.terms.IStrategoList) {
					final org.spoofax.interpreter.terms.IStrategoList t98027 = (org.spoofax.interpreter.terms.IStrategoList) t_in2717;
					if (t98027.isEmpty() == true) {
						final IValue lifted_31797 = new S_1(null, t98027);
						final ds.manual.interpreter.SState sheap_out3617 = sheap_in3617;
						final ds.manual.interpreter.VState vheap_out3257 = vheap_in3257;
						final boolean bool_out2717 = bool_in2717;
						final org.spoofax.interpreter.core.StackTracer trace_out2897 = trace_in2897;
						final IValue result_out4517 = lifted_31797;
						return new R_ma_Value(result_out4517, sheap_out3617,
								vheap_out3257, bool_out2717, trace_out2897);
					} else {
					}
				} else {
				}
			} else {
				if (lifted_31807 != null && !lifted_31807.isEmpty()) {
					final I_STerm p761 = lifted_31807.head();
					final INodeList<I_STerm> ps160 = lifted_31807.tail();
					if (t_in2717 instanceof org.spoofax.interpreter.terms.IStrategoList) {
						final org.spoofax.interpreter.terms.IStrategoList t98037 = (org.spoofax.interpreter.terms.IStrategoList) t_in2717;
						if (t98037.isEmpty() == false) {
							final org.spoofax.interpreter.terms.IStrategoTerm lifted_31827 = t98037
									.head();
							final I_Strategy lifted_31837 = new Match_1(null,
									p761);
							final R_default_Value $tmp2581 = lifted_31837
									.exec_default(ic_in2717, senv_in3077,
											venv_in3167, lifted_31827,
											tf_in2717, sheap_in3617,
											vheap_in3257, bool_in2717,
											trace_in2897);
							final IValue lifted_31847 = $tmp2581.value;
							final ds.manual.interpreter.SState sheap_29561 = $tmp2581
									.get_1();
							final ds.manual.interpreter.VState vheap_28915 = $tmp2581
									.get_2();
							final boolean bool_28476 = $tmp2581.get_3();
							final org.spoofax.interpreter.core.StackTracer trace_28539 = $tmp2581
									.get_4();
							final S_1 $tmp2582 = lifted_31847.match(S_1.class);
							if ($tmp2582 != null) {
								final org.spoofax.interpreter.terms.IStrategoTerm lifted_30007 = $tmp2582
										.get_1();
								final org.spoofax.interpreter.terms.IStrategoList lifted_31857 = t98037
										.tail();
								final I_Matcher lifted_31867 = new ml_1(null,
										ps160);
								final R_ma_Value $tmp2583 = lifted_31867
										.exec_ma(ic_in2717, senv_in3077,
												venv_in3167, tf_in2717,
												lifted_31857, sheap_29561,
												vheap_28915, bool_28476,
												trace_28539);
								final IValue lifted_31877 = $tmp2583.value;
								final ds.manual.interpreter.SState sheap_34708 = $tmp2583
										.get_1();
								final ds.manual.interpreter.VState vheap_34245 = $tmp2583
										.get_2();
								final boolean bool_33366 = $tmp2583.get_3();
								final org.spoofax.interpreter.core.StackTracer trace_33685 = $tmp2583
										.get_4();
								final S_1 $tmp2584 = lifted_31877
										.match(S_1.class);
								if ($tmp2584 != null) {
									final org.spoofax.interpreter.terms.IStrategoTerm lifted_30017 = $tmp2584
											.get_1();
									final IValue lifted_31817 = new S_1(null,
											t98037);
									final ds.manual.interpreter.SState sheap_out3617 = sheap_34708;
									final ds.manual.interpreter.VState vheap_out3257 = vheap_34245;
									final boolean bool_out2717 = bool_33366;
									final org.spoofax.interpreter.core.StackTracer trace_out2897 = trace_33685;
									final IValue result_out4517 = lifted_31817;
									return new R_ma_Value(result_out4517,
											sheap_out3617, vheap_out3257,
											bool_out2717, trace_out2897);
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
		}
		{
			final IValue lifted_31887 = new F_0(null);
			final ds.manual.interpreter.SState sheap_out3617 = sheap_in3617;
			final ds.manual.interpreter.VState vheap_out3257 = vheap_in3257;
			final boolean bool_out2717 = bool_in2717;
			final org.spoofax.interpreter.core.StackTracer trace_out2897 = trace_in2897;
			final IValue result_out4517 = lifted_31887;
			return new R_ma_Value(result_out4517, sheap_out3617, vheap_out3257,
					bool_out2717, trace_out2897);
		}
	}

	public INodeList<I_STerm> get_1() {
		return this._1;
	}
}
package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class bl_1 extends AbstractNode implements I_Builder {
	private boolean hasSpecialized;

	@Children
	public INodeList<I_STerm> _1;

	public bl_1(INodeSource source, INodeList<I_STerm> _1) {
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
		final bl_1 other = (bl_1) obj;
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

	public R_bld_BuildRes exec_bld(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			org.spoofax.interpreter.terms.IStrategoTerm _3,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _4,
			org.spoofax.interpreter.terms.ITermFactory _5,
			ds.manual.interpreter.SState _6, ds.manual.interpreter.VState _7,
			boolean _8, org.spoofax.interpreter.core.StackTracer _9) {
		this.specializeChildren(0);
		throw new InterpreterException("Rule failure");
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2721 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3081 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3171 = _3;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2721 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2721 = _5;
		final ds.manual.interpreter.SState sheap_in3621 = _6;
		final ds.manual.interpreter.VState vheap_in3261 = _7;
		final boolean bool_in2721 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2901 = _9;
		final INodeList<I_STerm> lifted_34017 = this._1;
		{
			if (lifted_34017 != null
					&& lifted_34017.equals(NodeList.NIL(Object.class))) {
				final I_BuildRes lifted_33997 = new BSS_1(
						null,
						NodeList.NIL(org.spoofax.interpreter.terms.IStrategoTerm.class));
				final ds.manual.interpreter.SState sheap_out3621 = sheap_in3621;
				final ds.manual.interpreter.VState vheap_out3261 = vheap_in3261;
				final boolean bool_out2721 = bool_in2721;
				final org.spoofax.interpreter.core.StackTracer trace_out2901 = trace_in2901;
				final I_BuildRes result_out4521 = lifted_33997;
				return new R_blds_BuildRes(result_out4521, sheap_out3621,
						vheap_out3261, bool_out2721, trace_out2901);
			} else {
				if (lifted_34017 != null && !lifted_34017.isEmpty()) {
					final I_STerm texpr195 = lifted_34017.head();
					final INodeList<I_STerm> texprs195 = lifted_34017.tail();
					final I_Strategy lifted_34037 = new Build_1(null, texpr195);
					final R_default_Value $tmp2613 = lifted_34037.exec_default(
							ic_in2721, senv_in3081, venv_in3171, t_in2721,
							tf_in2721, sheap_in3621, vheap_in3261, bool_in2721,
							trace_in2901);
					final IValue lifted_34047 = $tmp2613.value;
					final ds.manual.interpreter.SState sheap_29571 = $tmp2613
							.get_1();
					final ds.manual.interpreter.VState vheap_28926 = $tmp2613
							.get_2();
					final boolean bool_28486 = $tmp2613.get_3();
					final org.spoofax.interpreter.core.StackTracer trace_28549 = $tmp2613
							.get_4();
					final S_1 $tmp2614 = lifted_34047.match(S_1.class);
					if ($tmp2614 != null) {
						final org.spoofax.interpreter.terms.IStrategoTerm tx195 = $tmp2614
								.get_1();
						final I_Builder lifted_34057 = new bl_1(null, texprs195);
						final R_blds_BuildRes $tmp2615 = lifted_34057
								.exec_blds(ic_in2721, senv_in3081, venv_in3171,
										t_in2721, tf_in2721, sheap_29571,
										vheap_28926, bool_28486, trace_28549);
						final I_BuildRes lifted_34067 = $tmp2615.value;
						final ds.manual.interpreter.SState sheap_34711 = $tmp2615
								.get_1();
						final ds.manual.interpreter.VState vheap_34248 = $tmp2615
								.get_2();
						final boolean bool_33369 = $tmp2615.get_3();
						final org.spoofax.interpreter.core.StackTracer trace_33688 = $tmp2615
								.get_4();
						final BSS_1 $tmp2616 = lifted_34067.match(BSS_1.class);
						if ($tmp2616 != null) {
							final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> txs384 = $tmp2616
									.get_1();
							final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> lifted_34077 = new NodeList<org.spoofax.interpreter.terms.IStrategoTerm>(
									tx195, txs384);
							final I_BuildRes lifted_34027 = new BSS_1(null,
									lifted_34077);
							final ds.manual.interpreter.SState sheap_out3621 = sheap_34711;
							final ds.manual.interpreter.VState vheap_out3261 = vheap_34248;
							final boolean bool_out2721 = bool_33369;
							final org.spoofax.interpreter.core.StackTracer trace_out2901 = trace_33688;
							final I_BuildRes result_out4521 = lifted_34027;
							return new R_blds_BuildRes(result_out4521,
									sheap_out3621, vheap_out3261, bool_out2721,
									trace_out2901);
						} else {
						}
					} else {
					}
				} else {
				}
			}
		}
		{
			final I_BuildRes lifted_34087 = new BF_0(null);
			final ds.manual.interpreter.SState sheap_out3621 = sheap_in3621;
			final ds.manual.interpreter.VState vheap_out3261 = vheap_in3261;
			final boolean bool_out2721 = bool_in2721;
			final org.spoofax.interpreter.core.StackTracer trace_out2901 = trace_in2901;
			final I_BuildRes result_out4521 = lifted_34087;
			return new R_blds_BuildRes(result_out4521, sheap_out3621,
					vheap_out3261, bool_out2721, trace_out2901);
		}
	}

	public INodeList<I_STerm> get_1() {
		return this._1;
	}
}
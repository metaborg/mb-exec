package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class letEval_2 extends AbstractNode implements I_LetHelper {
	private boolean hasSpecialized;

	@Children
	public INodeList<I_Def> _1;

	@Child
	public I_Strategy _2;

	public letEval_2(INodeSource source, INodeList<I_Def> _1, I_Strategy _2) {
		this.setSourceInfo(source);
		this._1 = adoptChildren(_1);
		this._2 = adoptChild(_2);
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
		final letEval_2 other = (letEval_2) obj;
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
		return true;
	}

	@Override
	public void specializeChildren(int depth) {
		if (!hasSpecialized) {
			for (I_Def _1_elem : _1) {
				if (_1_elem instanceof IGenericNode) {
					((IGenericNode) _1_elem).specialize(depth);
				}
			}
			if (_2 instanceof IGenericNode) {
				((IGenericNode) _2).specialize(depth);
			}
			hasSpecialized = true;
		}
	}

	public R_leteval_Value exec_leteval(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			org.spoofax.interpreter.terms.IStrategoTerm _2,
			org.spoofax.interpreter.terms.ITermFactory _3,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _4,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _5,
			ds.manual.interpreter.SState _6, ds.manual.interpreter.VState _7,
			boolean _8, org.spoofax.interpreter.core.StackTracer _9) {
		this.specializeChildren(0);
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2728 = _1;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2728 = _2;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2728 = _3;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3181 = _4;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3090 = _5;
		final ds.manual.interpreter.SState sheap_in3630 = _6;
		final ds.manual.interpreter.VState vheap_in3269 = _7;
		final boolean bool_in2728 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2908 = _9;
		final INodeList<I_Def> lifted_33367 = this._1;
		final I_Strategy in_s230 = this._2;
		{
			if (lifted_33367 != null
					&& lifted_33367.equals(NodeList.NIL(Object.class))) {
				final R_default_Value $tmp2671 = in_s230.exec_default(
						ic_in2728, senv_in3090, venv_in3181, t_in2728,
						tf_in2728, sheap_in3630, vheap_in3269, bool_in2728,
						trace_in2908);
				final IValue v4125 = $tmp2671.value;
				final ds.manual.interpreter.SState sheap_29582 = $tmp2671
						.get_1();
				final ds.manual.interpreter.VState vheap_28936 = $tmp2671
						.get_2();
				final boolean bool_28495 = $tmp2671.get_3();
				final org.spoofax.interpreter.core.StackTracer trace_28558 = $tmp2671
						.get_4();
				final ds.manual.interpreter.SState sheap_out3630 = sheap_29582;
				final ds.manual.interpreter.VState vheap_out3269 = vheap_28936;
				final boolean bool_out2728 = bool_28495;
				final org.spoofax.interpreter.core.StackTracer trace_out2908 = trace_28558;
				final IValue result_out4531 = v4125;
				return new R_leteval_Value(result_out4531, sheap_out3630,
						vheap_out3269, bool_out2728, trace_out2908);
			} else {
				if (lifted_33367 != null && !lifted_33367.isEmpty()) {
					final I_Def sdef390 = lifted_33367.head();
					final INodeList<I_Def> sdefs941 = lifted_33367.tail();
					final SDefT_4 $tmp2672 = sdef390.match(SDefT_4.class);
					if ($tmp2672 != null) {
						final String sname1210 = $tmp2672.get_1();
						final INodeList<I_Typedid> ss868 = $tmp2672.get_2();
						final INodeList<I_Typedid> ts5870 = $tmp2672.get_3();
						final I_Strategy s11442 = $tmp2672.get_4();
						final I_NameExtractor lifted_33377 = new typedIds_1(
								null, ss868);
						final R_exid_List_String_ $tmp2673 = lifted_33377
								.exec_exid();
						final INodeList<String> ss_30 = $tmp2673.value;
						final I_NameExtractor lifted_33387 = new typedIds_1(
								null, ts5870);
						final R_exid_List_String_ $tmp2674 = lifted_33387
								.exec_exid();
						final INodeList<String> ts_106 = $tmp2674.value;
						final I_Thunk lifted_33417 = new Thunk_6(null,
								sname1210, ss_30, ts_106, s11442, venv_in3181,
								senv_in3090);
						final I_SHeapOp lifted_33397 = new SUpdate_3(null,
								senv_in3090, sname1210, lifted_33417);
						final R_salloc_SEnv $tmp2675 = lifted_33397
								.exec_salloc(sheap_in3630);
						final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> lifted_30457 = $tmp2675.value;
						final ds.manual.interpreter.SState sheap_29583 = $tmp2675
								.get_1();
						final I_LetHelper lifted_33407 = new letEval_2(null,
								sdefs941, in_s230);
						final R_leteval_Value $tmp2676 = lifted_33407
								.exec_leteval(ic_in2728, t_in2728, tf_in2728,
										venv_in3181, senv_in3090, sheap_29583,
										vheap_in3269, bool_in2728, trace_in2908);
						final IValue v4126 = $tmp2676.value;
						final ds.manual.interpreter.SState sheap_34721 = $tmp2676
								.get_1();
						final ds.manual.interpreter.VState vheap_28937 = $tmp2676
								.get_2();
						final boolean bool_28496 = $tmp2676.get_3();
						final org.spoofax.interpreter.core.StackTracer trace_28559 = $tmp2676
								.get_4();
						final ds.manual.interpreter.SState sheap_out3630 = sheap_34721;
						final ds.manual.interpreter.VState vheap_out3269 = vheap_28937;
						final boolean bool_out2728 = bool_28496;
						final org.spoofax.interpreter.core.StackTracer trace_out2908 = trace_28559;
						final IValue result_out4531 = v4126;
						return new R_leteval_Value(result_out4531,
								sheap_out3630, vheap_out3269, bool_out2728,
								trace_out2908);
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

	public INodeList<I_Def> get_1() {
		return this._1;
	}

	public I_Strategy get_2() {
		return this._2;
	}
}
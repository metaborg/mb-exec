package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Let_2 extends AbstractNode implements I_Strategy {
	private boolean hasSpecialized;

	@Children
	public INodeList<I_Def> _1;

	@Child
	public I_Strategy _2;

	public Let_2(INodeSource source, INodeList<I_Def> _1, I_Strategy _2) {
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
		final Let_2 other = (Let_2) obj;
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

	public R_default_Value exec_default(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _3,
			org.spoofax.interpreter.terms.IStrategoTerm _4,
			org.spoofax.interpreter.terms.ITermFactory _5,
			ds.manual.interpreter.SState _6, ds.manual.interpreter.VState _7,
			boolean _8, org.spoofax.interpreter.core.StackTracer _9) {
		this.specializeChildren(0);
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2714 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3074 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3164 = _3;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2714 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2714 = _5;
		final ds.manual.interpreter.SState sheap_in3614 = _6;
		final ds.manual.interpreter.VState vheap_in3254 = _7;
		final boolean bool_in2714 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2894 = _9;
		final INodeList<I_Def> sdefs940 = this._1;
		final I_Strategy s11433 = this._2;
		final I_NameExtractor lifted_33427 = new sdefNames_1(null, sdefs940);
		final R_exid_List_String_ $tmp2527 = lifted_33427.exec_exid();
		final INodeList<String> snames320 = $tmp2527.value;
		final I_SHeapOp lifted_33437 = new SParAlloc_2(null, senv_in3074,
				snames320);
		final R_salloc_SEnv $tmp2528 = lifted_33437.exec_salloc(sheap_in3614);
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> d_20 = $tmp2528.value;
		final ds.manual.interpreter.SState sheap_29547 = $tmp2528.get_1();
		final I_LetHelper lifted_33447 = new letEval_2(null, sdefs940, s11433);
		final R_leteval_Value $tmp2529 = lifted_33447.exec_leteval(ic_in2714,
				t_in2714, tf_in2714, venv_in3164, d_20, sheap_29547,
				vheap_in3254, bool_in2714, trace_in2894);
		final IValue v4123 = $tmp2529.value;
		final ds.manual.interpreter.SState sheap_34702 = $tmp2529.get_1();
		final ds.manual.interpreter.VState vheap_28901 = $tmp2529.get_2();
		final boolean bool_28462 = $tmp2529.get_3();
		final org.spoofax.interpreter.core.StackTracer trace_28525 = $tmp2529
				.get_4();
		final ds.manual.interpreter.SState sheap_out3614 = sheap_34702;
		final ds.manual.interpreter.VState vheap_out3254 = vheap_28901;
		final boolean bool_out2714 = bool_28462;
		final org.spoofax.interpreter.core.StackTracer trace_out2894 = trace_28525;
		final IValue result_out4514 = v4123;
		return new R_default_Value(result_out4514, sheap_out3614,
				vheap_out3254, bool_out2714, trace_out2894);
	}

	public INodeList<I_Def> get_1() {
		return this._1;
	}

	public I_Strategy get_2() {
		return this._2;
	}
}
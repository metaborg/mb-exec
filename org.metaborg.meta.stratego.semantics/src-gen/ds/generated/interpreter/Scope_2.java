package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Scope_2 extends AbstractNode implements I_Strategy {
	private boolean hasSpecialized;

	public INodeList<String> _1;

	@Child
	public I_Strategy _2;

	public Scope_2(INodeSource source, INodeList<String> _1, I_Strategy _2) {
		this.setSourceInfo(source);
		this._1 = _1;
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
		final Scope_2 other = (Scope_2) obj;
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2707 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3067 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3157 = _3;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2707 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2707 = _5;
		final ds.manual.interpreter.SState sheap_in3607 = _6;
		final ds.manual.interpreter.VState vheap_in3247 = _7;
		final boolean bool_in2707 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2887 = _9;
		final INodeList<String> vs393 = this._1;
		final I_Strategy s11431 = this._2;
		final I_VHeapOp lifted_31307 = new VPushBatch_2(null, venv_in3157,
				vs393);
		final R_vinit_VEnv $tmp2492 = lifted_31307.exec_vinit(vheap_in3247);
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> e_20 = $tmp2492.value;
		final ds.manual.interpreter.VState vheap_28892 = $tmp2492.get_1();
		final R_default_Value $tmp2493 = s11431.exec_default(ic_in2707,
				senv_in3067, e_20, t_in2707, tf_in2707, sheap_in3607,
				vheap_28892, bool_in2707, trace_in2887);
		final IValue v4118 = $tmp2493.value;
		final ds.manual.interpreter.SState sheap_29538 = $tmp2493.get_1();
		final ds.manual.interpreter.VState vheap_34232 = $tmp2493.get_2();
		final boolean bool_28453 = $tmp2493.get_3();
		final org.spoofax.interpreter.core.StackTracer trace_28516 = $tmp2493
				.get_4();
		final ds.manual.interpreter.SState sheap_out3607 = sheap_29538;
		final ds.manual.interpreter.VState vheap_out3247 = vheap_34232;
		final boolean bool_out2707 = bool_28453;
		final org.spoofax.interpreter.core.StackTracer trace_out2887 = trace_28516;
		final IValue result_out4507 = v4118;
		return new R_default_Value(result_out4507, sheap_out3607,
				vheap_out3247, bool_out2707, trace_out2887);
	}

	public INodeList<String> get_1() {
		return this._1;
	}

	public I_Strategy get_2() {
		return this._2;
	}
}
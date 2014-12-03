package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class PrimT_3 extends AbstractNode implements I_Strategy {
	private boolean hasSpecialized;

	public String _1;

	@Children
	public INodeList<I_Strategy> _2;

	@Children
	public INodeList<I_STerm> _3;

	public PrimT_3(INodeSource source, String _1, INodeList<I_Strategy> _2,
			INodeList<I_STerm> _3) {
		this.setSourceInfo(source);
		this._1 = _1;
		this._2 = adoptChildren(_2);
		this._3 = adoptChildren(_3);
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
		final PrimT_3 other = (PrimT_3) obj;
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
			for (I_STerm _3_elem : _3) {
				if (_3_elem instanceof IGenericNode) {
					((IGenericNode) _3_elem).specialize(depth);
				}
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2704 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3064 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3154 = _3;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2704 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2704 = _5;
		final ds.manual.interpreter.SState sheap_in3604 = _6;
		final ds.manual.interpreter.VState vheap_in3244 = _7;
		final boolean bool_in2704 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2884 = _9;
		final String sname1207 = this._1;
		final INodeList<I_Strategy> ass1005 = this._2;
		final INodeList<I_STerm> ats3825 = this._3;
		{
			final I_Builder lifted_31327 = new bl_1(null, ats3825);
			final R_blds_BuildRes $tmp2479 = lifted_31327.exec_blds(ic_in2704,
					senv_in3064, venv_in3154, t_in2704, tf_in2704,
					sheap_in3604, vheap_in3244, bool_in2704, trace_in2884);
			final I_BuildRes lifted_31337 = $tmp2479.value;
			final ds.manual.interpreter.SState sheap_29535 = $tmp2479.get_1();
			final ds.manual.interpreter.VState vheap_28889 = $tmp2479.get_2();
			final boolean bool_28450 = $tmp2479.get_3();
			final org.spoofax.interpreter.core.StackTracer trace_28513 = $tmp2479
					.get_4();
			final BSS_1 $tmp2480 = lifted_31337.match(BSS_1.class);
			if ($tmp2480 != null) {
				final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> ats_118 = $tmp2480
						.get_1();
				final I_Thunker lifted_31347 = new mkThunks_1(null, ass1005);
				final R_thunks_List_Thunk_ $tmp2481 = lifted_31347.exec_thunks(
						venv_in3154, senv_in3064, sheap_29535);
				final INodeList<I_Thunk> thunks189 = $tmp2481.value;
				final ds.manual.interpreter.SState sheap_34694 = $tmp2481
						.get_1();
				final I_Strategy lifted_31357 = new primCall_3(null, sname1207,
						thunks189, ats_118);
				final R_default_Value $tmp2482 = lifted_31357.exec_default(
						ic_in2704, senv_in3064, venv_in3154, t_in2704,
						tf_in2704, sheap_34694, vheap_28889, bool_28450,
						trace_28513);
				final IValue v4117 = $tmp2482.value;
				final ds.manual.interpreter.SState sheap_4556 = $tmp2482
						.get_1();
				final ds.manual.interpreter.VState vheap_34228 = $tmp2482
						.get_2();
				final boolean bool_33353 = $tmp2482.get_3();
				final org.spoofax.interpreter.core.StackTracer trace_33672 = $tmp2482
						.get_4();
				final ds.manual.interpreter.SState sheap_out3604 = sheap_4556;
				final ds.manual.interpreter.VState vheap_out3244 = vheap_34228;
				final boolean bool_out2704 = bool_33353;
				final org.spoofax.interpreter.core.StackTracer trace_out2884 = trace_33672;
				final IValue result_out4504 = v4117;
				return new R_default_Value(result_out4504, sheap_out3604,
						vheap_out3244, bool_out2704, trace_out2884);
			} else {
			}
		}
		{
			final IValue lifted_31367 = new F_0(null);
			final ds.manual.interpreter.SState sheap_out3604 = sheap_in3604;
			final ds.manual.interpreter.VState vheap_out3244 = vheap_in3244;
			final boolean bool_out2704 = bool_in2704;
			final org.spoofax.interpreter.core.StackTracer trace_out2884 = trace_in2884;
			final IValue result_out4504 = lifted_31367;
			return new R_default_Value(result_out4504, sheap_out3604,
					vheap_out3244, bool_out2704, trace_out2884);
		}
	}

	public String get_1() {
		return this._1;
	}

	public INodeList<I_Strategy> get_2() {
		return this._2;
	}

	public INodeList<I_STerm> get_3() {
		return this._3;
	}
}
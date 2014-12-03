package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class CallDynamic_3 extends AbstractNode implements I_Strategy {
	private boolean hasSpecialized;

	@Child
	public I_STerm _1;

	@Children
	public INodeList<I_Strategy> _2;

	@Children
	public INodeList<I_STerm> _3;

	public CallDynamic_3(INodeSource source, I_STerm _1,
			INodeList<I_Strategy> _2, INodeList<I_STerm> _3) {
		this.setSourceInfo(source);
		this._1 = adoptChild(_1);
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
		final CallDynamic_3 other = (CallDynamic_3) obj;
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
			if (_1 instanceof IGenericNode) {
				((IGenericNode) _1).specialize(depth);
			}
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2712 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3072 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3162 = _3;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2712 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2712 = _5;
		final ds.manual.interpreter.SState sheap_in3612 = _6;
		final ds.manual.interpreter.VState vheap_in3252 = _7;
		final boolean bool_in2712 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2892 = _9;
		final I_STerm target189 = this._1;
		final INodeList<I_Strategy> ass1006 = this._2;
		final INodeList<I_STerm> ats3826 = this._3;
		{
			final I_Strategy lifted_33697 = new Build_1(null, target189);
			final R_default_Value $tmp2515 = lifted_33697.exec_default(
					ic_in2712, senv_in3072, venv_in3162, t_in2712, tf_in2712,
					sheap_in3612, vheap_in3252, bool_in2712, trace_in2892);
			final IValue lifted_33707 = $tmp2515.value;
			final ds.manual.interpreter.SState sheap_29545 = $tmp2515.get_1();
			final ds.manual.interpreter.VState vheap_28899 = $tmp2515.get_2();
			final boolean bool_28460 = $tmp2515.get_3();
			final org.spoofax.interpreter.core.StackTracer trace_28523 = $tmp2515
					.get_4();
			final S_1 $tmp2516 = lifted_33707.match(S_1.class);
			if ($tmp2516 != null) {
				final org.spoofax.interpreter.terms.IStrategoTerm sname_aterm195 = $tmp2516
						.get_1();
				if (sname_aterm195 instanceof org.spoofax.interpreter.terms.IStrategoString) {
					final org.spoofax.interpreter.terms.IStrategoString sname_aterm1827 = (org.spoofax.interpreter.terms.IStrategoString) sname_aterm195;
					final String sname1208 = sname_aterm1827.stringValue();
					final I_SVar lifted_33727 = new SVar_1(null, sname1208);
					final I_Strategy lifted_33717 = new CallT_3(null,
							lifted_33727, ass1006, ats3826);
					final R_default_Value $tmp2517 = lifted_33717.exec_default(
							ic_in2712, senv_in3072, venv_in3162, t_in2712,
							tf_in2712, sheap_29545, vheap_28899, bool_28460,
							trace_28523);
					final IValue v4121 = $tmp2517.value;
					final ds.manual.interpreter.SState sheap_34700 = $tmp2517
							.get_1();
					final ds.manual.interpreter.VState vheap_34235 = $tmp2517
							.get_2();
					final boolean bool_33359 = $tmp2517.get_3();
					final org.spoofax.interpreter.core.StackTracer trace_33678 = $tmp2517
							.get_4();
					final ds.manual.interpreter.SState sheap_out3612 = sheap_34700;
					final ds.manual.interpreter.VState vheap_out3252 = vheap_34235;
					final boolean bool_out2712 = bool_33359;
					final org.spoofax.interpreter.core.StackTracer trace_out2892 = trace_33678;
					final IValue result_out4512 = v4121;
					return new R_default_Value(result_out4512, sheap_out3612,
							vheap_out3252, bool_out2712, trace_out2892);
				} else {
				}
			} else {
			}
		}
		{
			throw new InterpreterException("Rule failure");
		}
	}

	public I_STerm get_1() {
		return this._1;
	}

	public INodeList<I_Strategy> get_2() {
		return this._2;
	}

	public INodeList<I_STerm> get_3() {
		return this._3;
	}
}
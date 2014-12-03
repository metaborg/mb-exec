package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class bAppl_1 extends AbstractNode implements I_Builder {
	private boolean hasSpecialized;

	@Child
	public I_PreTerm _1;

	public bAppl_1(INodeSource source, I_PreTerm _1) {
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
		final bAppl_1 other = (bAppl_1) obj;
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2722 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3082 = _2;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2722 = _3;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3172 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2722 = _5;
		final ds.manual.interpreter.SState sheap_in3622 = _6;
		final ds.manual.interpreter.VState vheap_in3262 = _7;
		final boolean bool_in2722 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2902 = _9;
		final I_PreTerm lifted_34517 = this._1;
		{
			final Op_2 $tmp2617 = lifted_34517.match(Op_2.class);
			if ($tmp2617 != null) {
				final String c1359 = $tmp2617.get_1();
				final INodeList<I_STerm> ts5868 = $tmp2617.get_2();
				final int numts195 = ds.manual.interpreter.Natives
						.length_1(ts5868);
				final org.spoofax.interpreter.terms.IStrategoConstructor constr874 = tf_in2722
						.makeConstructor(c1359, numts195);
				final I_Builder lifted_34537 = new bl_1(null, ts5868);
				final R_blds_BuildRes $tmp2618 = lifted_34537.exec_blds(
						ic_in2722, senv_in3082, venv_in3172, t_in2722,
						tf_in2722, sheap_in3622, vheap_in3262, bool_in2722,
						trace_in2902);
				final I_BuildRes lifted_34547 = $tmp2618.value;
				final ds.manual.interpreter.SState sheap_29572 = $tmp2618
						.get_1();
				final ds.manual.interpreter.VState vheap_28927 = $tmp2618
						.get_2();
				final boolean bool_28487 = $tmp2618.get_3();
				final org.spoofax.interpreter.core.StackTracer trace_28550 = $tmp2618
						.get_4();
				final BSS_1 $tmp2619 = lifted_34547.match(BSS_1.class);
				if ($tmp2619 != null) {
					final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> ts_104 = $tmp2619
							.get_1();
					final org.spoofax.interpreter.terms.IStrategoTerm[] ts__31 = ds.manual.interpreter.Natives
							.List2TARRAY_1(ts_104);
					final org.spoofax.interpreter.terms.IStrategoAppl appl391 = tf_in2722
							.makeAppl(constr874, ts__31);
					final I_BuildRes lifted_34527 = new BS_1(null, appl391);
					final ds.manual.interpreter.SState sheap_out3622 = sheap_29572;
					final ds.manual.interpreter.VState vheap_out3262 = vheap_28927;
					final boolean bool_out2722 = bool_28487;
					final org.spoofax.interpreter.core.StackTracer trace_out2902 = trace_28550;
					final I_BuildRes result_out4522 = lifted_34527;
					return new R_bld_BuildRes(result_out4522, sheap_out3622,
							vheap_out3262, bool_out2722, trace_out2902);
				} else {
				}
			} else {
			}
		}
		{
			final I_BuildRes lifted_34557 = new BF_0(null);
			final ds.manual.interpreter.SState sheap_out3622 = sheap_in3622;
			final ds.manual.interpreter.VState vheap_out3262 = vheap_in3262;
			final boolean bool_out2722 = bool_in2722;
			final org.spoofax.interpreter.core.StackTracer trace_out2902 = trace_in2902;
			final I_BuildRes result_out4522 = lifted_34557;
			return new R_bld_BuildRes(result_out4522, sheap_out3622,
					vheap_out3262, bool_out2722, trace_out2902);
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
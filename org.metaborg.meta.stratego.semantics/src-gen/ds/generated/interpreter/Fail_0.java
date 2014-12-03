package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Fail_0 extends AbstractNode implements I_Strategy {
	private boolean hasSpecialized;

	public Fail_0(INodeSource source) {
		this.setSourceInfo(source);
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
		final Fail_0 other = (Fail_0) obj;
		return true;
	}

	@Override
	public void specializeChildren(int depth) {
		if (!hasSpecialized) {
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2711 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3071 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3161 = _3;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2711 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2711 = _5;
		final ds.manual.interpreter.SState sheap_in3611 = _6;
		final ds.manual.interpreter.VState vheap_in3251 = _7;
		final boolean bool_in2711 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2891 = _9;
		final IValue lifted_33597 = new F_0(null);
		final ds.manual.interpreter.SState sheap_out3611 = sheap_in3611;
		final ds.manual.interpreter.VState vheap_out3251 = vheap_in3251;
		final boolean bool_out2711 = bool_in2711;
		final org.spoofax.interpreter.core.StackTracer trace_out2891 = trace_in2891;
		final IValue result_out4511 = lifted_33597;
		return new R_default_Value(result_out4511, sheap_out3611,
				vheap_out3251, bool_out2711, trace_out2891);
	}
}
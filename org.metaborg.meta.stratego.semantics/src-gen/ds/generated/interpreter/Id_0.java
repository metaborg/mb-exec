package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Id_0 extends AbstractNode implements I_Strategy {
	private boolean hasSpecialized;

	public Id_0(INodeSource source) {
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
		final Id_0 other = (Id_0) obj;
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2710 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3070 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3160 = _3;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2710 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2710 = _5;
		final ds.manual.interpreter.SState sheap_in3610 = _6;
		final ds.manual.interpreter.VState vheap_in3250 = _7;
		final boolean bool_in2710 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2890 = _9;
		final IValue lifted_33487 = new S_1(null, t_in2710);
		final ds.manual.interpreter.SState sheap_out3610 = sheap_in3610;
		final ds.manual.interpreter.VState vheap_out3250 = vheap_in3250;
		final boolean bool_out2710 = bool_in2710;
		final org.spoofax.interpreter.core.StackTracer trace_out2890 = trace_in2890;
		final IValue result_out4510 = lifted_33487;
		return new R_default_Value(result_out4510, sheap_out3610,
				vheap_out3250, bool_out2710, trace_out2890);
	}
}
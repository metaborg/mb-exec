package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class ImportTerm_1 extends AbstractNode implements I_Strategy {
	private boolean hasSpecialized;

	public String _1;

	public ImportTerm_1(INodeSource source, String _1) {
		this.setSourceInfo(source);
		this._1 = _1;
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
		final ImportTerm_1 other = (ImportTerm_1) obj;
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
		final ds.manual.interpreter.AutoInterpInteropContext ic_in2715 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3075 = _2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3165 = _3;
		final org.spoofax.interpreter.terms.IStrategoTerm t_in2715 = _4;
		final org.spoofax.interpreter.terms.ITermFactory tf_in2715 = _5;
		final ds.manual.interpreter.SState sheap_in3615 = _6;
		final ds.manual.interpreter.VState vheap_in3255 = _7;
		final boolean bool_in2715 = _8;
		final org.spoofax.interpreter.core.StackTracer trace_in2895 = _9;
		final String p759 = this._1;
		final org.spoofax.interpreter.terms.IStrategoTerm t10086 = ds.manual.interpreter.Natives
				.importTerm_2(ic_in2715, p759);
		final IValue lifted_33477 = new S_1(null, t10086);
		final ds.manual.interpreter.SState sheap_out3615 = sheap_in3615;
		final ds.manual.interpreter.VState vheap_out3255 = vheap_in3255;
		final boolean bool_out2715 = bool_in2715;
		final org.spoofax.interpreter.core.StackTracer trace_out2895 = trace_in2895;
		final IValue result_out4515 = lifted_33477;
		return new R_default_Value(result_out4515, sheap_out3615,
				vheap_out3255, bool_out2715, trace_out2895);
	}

	public String get_1() {
		return this._1;
	}
}
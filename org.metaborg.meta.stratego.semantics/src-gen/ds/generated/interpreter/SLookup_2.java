package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class SLookup_2 extends AbstractNode implements I_SHeapOp {
	private boolean hasSpecialized;

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _1;

	public String _2;

	public SLookup_2(
			INodeSource source,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _1,
			String _2) {
		this.setSourceInfo(source);
		this._1 = _1;
		this._2 = _2;
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
		final SLookup_2 other = (SLookup_2) obj;
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
			hasSpecialized = true;
		}
	}

	public R_slook_SLookupResult exec_slook(ds.manual.interpreter.SState _1) {
		this.specializeChildren(0);
		final ds.manual.interpreter.SState sheap_in3638 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv3003 = this._1;
		final String name2464 = this._2;
		final I_Thunk thunk1741 = sheap_in3638.lookup(senv3003, name2464);
		final I_SLookupResult lifted_31217 = new SLookupResult_2(null,
				senv3003, thunk1741);
		final ds.manual.interpreter.SState sheap_out3638 = sheap_in3638;
		final I_SLookupResult result_out4546 = lifted_31217;
		return new R_slook_SLookupResult(result_out4546, sheap_out3638);
	}

	public R_salloc_SEnv exec_salloc(ds.manual.interpreter.SState _1) {
		this.specializeChildren(0);
		throw new InterpreterException("Rule failure");
	}

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> get_1() {
		return this._1;
	}

	public String get_2() {
		return this._2;
	}
}
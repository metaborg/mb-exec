package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class SAlloc_2 extends AbstractNode implements I_SHeapOp {
	private boolean hasSpecialized;

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _1;

	public String _2;

	public SAlloc_2(
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
		final SAlloc_2 other = (SAlloc_2) obj;
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
		throw new InterpreterException("Rule failure");
	}

	public R_salloc_SEnv exec_salloc(ds.manual.interpreter.SState _1) {
		this.specializeChildren(0);
		final ds.manual.interpreter.SState sheap_in3634 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv2999 = this._1;
		final String name2460 = this._2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_51 = sheap_in3634
				.alloc(senv2999, name2460);
		final ds.manual.interpreter.SState sheap_out3634 = sheap_in3634;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> result_out4542 = senv_51;
		return new R_salloc_SEnv(result_out4542, sheap_out3634);
	}

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> get_1() {
		return this._1;
	}

	public String get_2() {
		return this._2;
	}
}
package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class SUpdate_3 extends AbstractNode implements I_SHeapOp {
	private boolean hasSpecialized;

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _1;

	public String _2;

	@Child
	public I_Thunk _3;

	public SUpdate_3(
			INodeSource source,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _1,
			String _2, I_Thunk _3) {
		this.setSourceInfo(source);
		this._1 = _1;
		this._2 = _2;
		this._3 = adoptChild(_3);
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
		final SUpdate_3 other = (SUpdate_3) obj;
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
			if (_3 instanceof IGenericNode) {
				((IGenericNode) _3).specialize(depth);
			}
			hasSpecialized = true;
		}
	}

	public R_slook_SLookupResult exec_slook(ds.manual.interpreter.SState _1) {
		this.specializeChildren(0);
		throw new InterpreterException("Rule failure");
	}

	public R_salloc_SEnv exec_salloc(ds.manual.interpreter.SState _1) {
		this.specializeChildren(0);
		final ds.manual.interpreter.SState sheap_in3636 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv3001 = this._1;
		final String name2462 = this._2;
		final I_Thunk thunk1739 = this._3;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_53 = sheap_in3636
				.update(senv3001, name2462, thunk1739);
		final ds.manual.interpreter.SState sheap_out3636 = sheap_in3636;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> result_out4544 = senv_53;
		return new R_salloc_SEnv(result_out4544, sheap_out3636);
	}

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> get_1() {
		return this._1;
	}

	public String get_2() {
		return this._2;
	}

	public I_Thunk get_3() {
		return this._3;
	}
}
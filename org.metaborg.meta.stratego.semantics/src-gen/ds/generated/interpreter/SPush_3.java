package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class SPush_3 extends AbstractNode implements I_SHeapOp {
	private boolean hasSpecialized;

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _1;

	public String _2;

	@Child
	public I_Thunk _3;

	public SPush_3(
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
		final SPush_3 other = (SPush_3) obj;
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
		final ds.manual.interpreter.SState sheap_in3637 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv3002 = this._1;
		final String name2463 = this._2;
		final I_Thunk thunk1740 = this._3;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_54 = sheap_in3637
				.allocupdate(senv3002, name2463, thunk1740);
		final ds.manual.interpreter.SState sheap_out3637 = sheap_in3637;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> result_out4545 = senv_54;
		return new R_salloc_SEnv(result_out4545, sheap_out3637);
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
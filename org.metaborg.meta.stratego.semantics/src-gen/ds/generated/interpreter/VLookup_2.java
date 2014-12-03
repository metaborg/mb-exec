package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class VLookup_2 extends AbstractNode implements I_VHeapOp {
	private boolean hasSpecialized;

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1;

	public String _2;

	public VLookup_2(
			INodeSource source,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
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
		final VLookup_2 other = (VLookup_2) obj;
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

	public R_vlook_VLookupResult exec_vlook(ds.manual.interpreter.VState _1) {
		this.specializeChildren(0);
		final ds.manual.interpreter.VState vheap_in3270 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv2265 = this._1;
		final String x3756 = this._2;
		final IValue v4127 = vheap_in3270.lookup(venv2265, x3756);
		final I_VLookupResult lifted_30847 = new VLookupResult_2(null,
				venv2265, v4127);
		final ds.manual.interpreter.VState vheap_out3270 = vheap_in3270;
		final I_VLookupResult result_out4537 = lifted_30847;
		return new R_vlook_VLookupResult(result_out4537, vheap_out3270);
	}

	public R_vupdate_VEnv exec_vupdate(ds.manual.interpreter.VState _1) {
		this.specializeChildren(0);
		throw new InterpreterException("Rule failure");
	}

	public R_vinit_VEnv exec_vinit(ds.manual.interpreter.VState _1) {
		this.specializeChildren(0);
		throw new InterpreterException("Rule failure");
	}

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> get_1() {
		return this._1;
	}

	public String get_2() {
		return this._2;
	}
}
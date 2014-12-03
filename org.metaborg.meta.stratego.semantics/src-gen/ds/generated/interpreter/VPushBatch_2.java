package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class VPushBatch_2 extends AbstractNode implements I_VHeapOp {
	private boolean hasSpecialized;

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1;

	public INodeList<String> _2;

	public VPushBatch_2(
			INodeSource source,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
			INodeList<String> _2) {
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
		final VPushBatch_2 other = (VPushBatch_2) obj;
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
		throw new InterpreterException("Rule failure");
	}

	public R_vupdate_VEnv exec_vupdate(ds.manual.interpreter.VState _1) {
		this.specializeChildren(0);
		throw new InterpreterException("Rule failure");
	}

	public R_vinit_VEnv exec_vinit(ds.manual.interpreter.VState _1) {
		this.specializeChildren(0);
		final ds.manual.interpreter.VState vheap_in3273 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv2268 = this._1;
		final INodeList<String> xs1002 = this._2;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_31 = vheap_in3273
				.allocs(venv2268, xs1002);
		final ds.manual.interpreter.VState vheap_out3273 = vheap_in3273;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> result_out4540 = venv_31;
		return new R_vinit_VEnv(result_out4540, vheap_out3273);
	}

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> get_1() {
		return this._1;
	}

	public INodeList<String> get_2() {
		return this._2;
	}
}
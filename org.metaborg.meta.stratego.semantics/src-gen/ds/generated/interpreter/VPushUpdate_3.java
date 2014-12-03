package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class VPushUpdate_3 extends AbstractNode implements I_VHeapOp {
	private boolean hasSpecialized;

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1;

	public String _2;

	public IValue _3;

	public VPushUpdate_3(
			INodeSource source,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
			String _2, IValue _3) {
		this.setSourceInfo(source);
		this._1 = _1;
		this._2 = _2;
		this._3 = _3;
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
		final VPushUpdate_3 other = (VPushUpdate_3) obj;
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
		final ds.manual.interpreter.VState vheap_in3274 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv2269 = this._1;
		final String x3759 = this._2;
		final IValue v4129 = this._3;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_32 = vheap_in3274
				.allocupdate(venv2269, x3759, v4129);
		final ds.manual.interpreter.VState vheap_out3274 = vheap_in3274;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> result_out4541 = venv_32;
		return new R_vinit_VEnv(result_out4541, vheap_out3274);
	}

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> get_1() {
		return this._1;
	}

	public String get_2() {
		return this._2;
	}

	public IValue get_3() {
		return this._3;
	}
}
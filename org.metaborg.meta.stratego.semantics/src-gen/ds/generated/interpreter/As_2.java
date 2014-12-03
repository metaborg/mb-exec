package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class As_2 extends AbstractNode implements I_PreTerm {
	private boolean hasSpecialized;

	@Child
	public I_Var _1;

	@Child
	public I_PreTerm _2;

	public As_2(INodeSource source, I_Var _1, I_PreTerm _2) {
		this.setSourceInfo(source);
		this._1 = adoptChild(_1);
		this._2 = adoptChild(_2);
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
		final As_2 other = (As_2) obj;
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
			if (_1 instanceof IGenericNode) {
				((IGenericNode) _1).specialize(depth);
			}
			if (_2 instanceof IGenericNode) {
				((IGenericNode) _2).specialize(depth);
			}
			hasSpecialized = true;
		}
	}

	public I_Var get_1() {
		return this._1;
	}

	public I_PreTerm get_2() {
		return this._2;
	}
}
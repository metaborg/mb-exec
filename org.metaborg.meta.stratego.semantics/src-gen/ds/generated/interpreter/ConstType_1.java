package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class ConstType_1 extends AbstractNode implements I_ConstType {
	private boolean hasSpecialized;

	@Child
	public I_Sort _1;

	public ConstType_1(INodeSource source, I_Sort _1) {
		this.setSourceInfo(source);
		this._1 = adoptChild(_1);
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
		final ConstType_1 other = (ConstType_1) obj;
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
			if (_1 instanceof IGenericNode) {
				((IGenericNode) _1).specialize(depth);
			}
			hasSpecialized = true;
		}
	}

	public I_Sort get_1() {
		return this._1;
	}
}
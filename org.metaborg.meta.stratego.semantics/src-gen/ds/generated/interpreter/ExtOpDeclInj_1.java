package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class ExtOpDeclInj_1 extends AbstractNode implements I_Opdecl {
	private boolean hasSpecialized;

	@Child
	public I_Type _1;

	public ExtOpDeclInj_1(INodeSource source, I_Type _1) {
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
		final ExtOpDeclInj_1 other = (ExtOpDeclInj_1) obj;
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

	public I_Type get_1() {
		return this._1;
	}
}
package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class ExtOpDeclQ_2 extends AbstractNode implements I_Opdecl {
	private boolean hasSpecialized;

	public String _1;

	@Child
	public I_Type _2;

	public ExtOpDeclQ_2(INodeSource source, String _1, I_Type _2) {
		this.setSourceInfo(source);
		this._1 = _1;
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
		final ExtOpDeclQ_2 other = (ExtOpDeclQ_2) obj;
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
			if (_2 instanceof IGenericNode) {
				((IGenericNode) _2).specialize(depth);
			}
			hasSpecialized = true;
		}
	}

	public String get_1() {
		return this._1;
	}

	public I_Type get_2() {
		return this._2;
	}
}
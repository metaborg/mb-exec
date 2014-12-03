package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Sort_2 extends AbstractNode implements I_Sort {
	private boolean hasSpecialized;

	public String _1;

	@Children
	public INodeList<I_Sort> _2;

	public Sort_2(INodeSource source, String _1, INodeList<I_Sort> _2) {
		this.setSourceInfo(source);
		this._1 = _1;
		this._2 = adoptChildren(_2);
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
		final Sort_2 other = (Sort_2) obj;
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
			for (I_Sort _2_elem : _2) {
				if (_2_elem instanceof IGenericNode) {
					((IGenericNode) _2_elem).specialize(depth);
				}
			}
			hasSpecialized = true;
		}
	}

	public String get_1() {
		return this._1;
	}

	public INodeList<I_Sort> get_2() {
		return this._2;
	}
}
package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class NoAnnoList_1 extends AbstractNode implements I_STerm {
	private boolean hasSpecialized;

	@Child
	public I_PreTerm _1;

	public NoAnnoList_1(INodeSource source, I_PreTerm _1) {
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
		final NoAnnoList_1 other = (NoAnnoList_1) obj;
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

	public I_PreTerm get_1() {
		return this._1;
	}
}
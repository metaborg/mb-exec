package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class SortVar_1 extends AbstractNode implements I_Sort {
	private boolean hasSpecialized;

	public String _1;

	public SortVar_1(INodeSource source, String _1) {
		this.setSourceInfo(source);
		this._1 = _1;
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
		final SortVar_1 other = (SortVar_1) obj;
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
			hasSpecialized = true;
		}
	}

	public String get_1() {
		return this._1;
	}
}
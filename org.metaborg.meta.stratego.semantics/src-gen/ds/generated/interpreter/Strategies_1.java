package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Strategies_1 extends AbstractNode implements I_Decl {
	private boolean hasSpecialized;

	@Children
	public INodeList<I_Def> _1;

	public Strategies_1(INodeSource source, INodeList<I_Def> _1) {
		this.setSourceInfo(source);
		this._1 = adoptChildren(_1);
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
		final Strategies_1 other = (Strategies_1) obj;
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
			for (I_Def _1_elem : _1) {
				if (_1_elem instanceof IGenericNode) {
					((IGenericNode) _1_elem).specialize(depth);
				}
			}
			hasSpecialized = true;
		}
	}

	public INodeList<I_Def> get_1() {
		return this._1;
	}
}
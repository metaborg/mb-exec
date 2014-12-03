package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class allocdDefs_2 extends AbstractNode implements I_AllocatorResult {
	private boolean hasSpecialized;

	@Children
	public INodeList<I_Def> _1;

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2;

	public allocdDefs_2(
			INodeSource source,
			INodeList<I_Def> _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2) {
		this.setSourceInfo(source);
		this._1 = adoptChildren(_1);
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
		final allocdDefs_2 other = (allocdDefs_2) obj;
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

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> get_2() {
		return this._2;
	}
}
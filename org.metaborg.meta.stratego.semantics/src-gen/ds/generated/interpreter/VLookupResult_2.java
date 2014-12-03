package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class VLookupResult_2 extends AbstractNode implements I_VLookupResult {
	private boolean hasSpecialized;

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1;

	public IValue _2;

	public VLookupResult_2(
			INodeSource source,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
			IValue _2) {
		this.setSourceInfo(source);
		this._1 = _1;
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
		final VLookupResult_2 other = (VLookupResult_2) obj;
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
			hasSpecialized = true;
		}
	}

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> get_1() {
		return this._1;
	}

	public IValue get_2() {
		return this._2;
	}
}
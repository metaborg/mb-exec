package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Thunk_6 extends AbstractNode implements I_Thunk {
	private boolean hasSpecialized;

	public String _1;

	public INodeList<String> _2;

	public INodeList<String> _3;

	@Child
	public I_Strategy _4;

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _5;

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _6;

	public Thunk_6(
			INodeSource source,
			String _1,
			INodeList<String> _2,
			INodeList<String> _3,
			I_Strategy _4,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _5,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _6) {
		this.setSourceInfo(source);
		this._1 = _1;
		this._2 = _2;
		this._3 = _3;
		this._4 = adoptChild(_4);
		this._5 = _5;
		this._6 = _6;
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
		final Thunk_6 other = (Thunk_6) obj;
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
		if (_3 == null) {
			if (other._3 != null) {
				return false;
			}
		} else if (!_3.equals(other._3)) {
			return false;
		}
		if (_4 == null) {
			if (other._4 != null) {
				return false;
			}
		} else if (!_4.equals(other._4)) {
			return false;
		}
		if (_5 == null) {
			if (other._5 != null) {
				return false;
			}
		} else if (!_5.equals(other._5)) {
			return false;
		}
		if (_6 == null) {
			if (other._6 != null) {
				return false;
			}
		} else if (!_6.equals(other._6)) {
			return false;
		}
		return true;
	}

	@Override
	public void specializeChildren(int depth) {
		if (!hasSpecialized) {
			if (_4 instanceof IGenericNode) {
				((IGenericNode) _4).specialize(depth);
			}
			hasSpecialized = true;
		}
	}

	public String get_1() {
		return this._1;
	}

	public INodeList<String> get_2() {
		return this._2;
	}

	public INodeList<String> get_3() {
		return this._3;
	}

	public I_Strategy get_4() {
		return this._4;
	}

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> get_5() {
		return this._5;
	}

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> get_6() {
		return this._6;
	}
}
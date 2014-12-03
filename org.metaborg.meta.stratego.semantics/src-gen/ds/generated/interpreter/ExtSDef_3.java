package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class ExtSDef_3 extends AbstractNode implements I_StrategyDef {
	private boolean hasSpecialized;

	public String _1;

	@Children
	public INodeList<I_Typedid> _2;

	@Children
	public INodeList<I_Typedid> _3;

	public ExtSDef_3(INodeSource source, String _1, INodeList<I_Typedid> _2,
			INodeList<I_Typedid> _3) {
		this.setSourceInfo(source);
		this._1 = _1;
		this._2 = adoptChildren(_2);
		this._3 = adoptChildren(_3);
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
		final ExtSDef_3 other = (ExtSDef_3) obj;
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
		return true;
	}

	@Override
	public void specializeChildren(int depth) {
		if (!hasSpecialized) {
			for (I_Typedid _2_elem : _2) {
				if (_2_elem instanceof IGenericNode) {
					((IGenericNode) _2_elem).specialize(depth);
				}
			}
			for (I_Typedid _3_elem : _3) {
				if (_3_elem instanceof IGenericNode) {
					((IGenericNode) _3_elem).specialize(depth);
				}
			}
			hasSpecialized = true;
		}
	}

	public String get_1() {
		return this._1;
	}

	public INodeList<I_Typedid> get_2() {
		return this._2;
	}

	public INodeList<I_Typedid> get_3() {
		return this._3;
	}
}
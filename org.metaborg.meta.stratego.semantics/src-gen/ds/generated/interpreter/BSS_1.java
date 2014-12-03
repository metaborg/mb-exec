package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class BSS_1 extends AbstractNode implements I_BuildRes {
	private boolean hasSpecialized;

	public INodeList<org.spoofax.interpreter.terms.IStrategoTerm> _1;

	public BSS_1(INodeSource source,
			INodeList<org.spoofax.interpreter.terms.IStrategoTerm> _1) {
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
		final BSS_1 other = (BSS_1) obj;
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

	public INodeList<org.spoofax.interpreter.terms.IStrategoTerm> get_1() {
		return this._1;
	}
}
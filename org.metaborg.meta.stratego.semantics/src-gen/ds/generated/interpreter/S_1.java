package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class S_1 extends AbstractNode implements IValue {
	private boolean hasSpecialized;

	public org.spoofax.interpreter.terms.IStrategoTerm _1;

	public S_1(INodeSource source,
			org.spoofax.interpreter.terms.IStrategoTerm _1) {
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
		final S_1 other = (S_1) obj;
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

	public org.spoofax.interpreter.terms.IStrategoTerm get_1() {
		return this._1;
	}
}
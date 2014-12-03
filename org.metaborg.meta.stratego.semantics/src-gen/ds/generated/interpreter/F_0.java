package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class F_0 extends AbstractNode implements IValue {
	private boolean hasSpecialized;

	public F_0(INodeSource source) {
		this.setSourceInfo(source);
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
		final F_0 other = (F_0) obj;
		return true;
	}

	@Override
	public void specializeChildren(int depth) {
		if (!hasSpecialized) {
			hasSpecialized = true;
		}
	}
}
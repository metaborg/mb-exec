package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class ThunkPlaceholder_0 extends AbstractNode implements I_Thunk {
	private boolean hasSpecialized;

	public ThunkPlaceholder_0(INodeSource source) {
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
		final ThunkPlaceholder_0 other = (ThunkPlaceholder_0) obj;
		return true;
	}

	@Override
	public void specializeChildren(int depth) {
		if (!hasSpecialized) {
			hasSpecialized = true;
		}
	}
}
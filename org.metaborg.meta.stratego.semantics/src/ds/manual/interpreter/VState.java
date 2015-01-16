package ds.manual.interpreter;

import org.metaborg.meta.interpreter.framework.INodeList;

import com.github.krukow.clj_ds.PersistentMap;

import ds.generated.interpreter.F_0;
import ds.generated.interpreter.I_Value;

public class VState {

	private static F_0 failCons = new F_0(null);

	public I_Value lookup(PersistentMap<String, VBox> venv, String x) {
		return ((VBox) venv.get(x)).value;
	}

	public PersistentMap<String, VBox> alloc(PersistentMap<String, VBox> venv,
			String x) {
		return venv.plus(x, new VBox(failCons));
	}

	public PersistentMap<String, VBox> allocs(PersistentMap<String, VBox> venv,
			INodeList<String> xs) {
		for (String x : xs) {
			venv = venv.plus(x, new VBox(failCons));
		}
		return venv;
	}

	public PersistentMap<String, VBox> allocupdate(
			PersistentMap<String, VBox> venv, String x, I_Value v) {
		return venv.plus(x, new VBox(v));
	}

	public PersistentMap<String, VBox> update(PersistentMap<String, VBox> venv,
			String x, I_Value v) {
		VBox box = (VBox) venv.get(x);
		box.value = v;
		return venv;
	}

}

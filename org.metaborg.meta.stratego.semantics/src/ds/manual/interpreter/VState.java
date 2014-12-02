package ds.manual.interpreter;

import org.metaborg.meta.interpreter.framework.INodeList;
import org.metaborg.meta.interpreter.framework.IValue;

import com.github.krukow.clj_ds.PersistentMap;

import ds.generated.interpreter.F_0;

public class VState {

	private static F_0 failCons = new F_0(null);

	public IValue lookup(PersistentMap<String, VBox> venv, String x) {
		return ((VBox) venv.get(x)).value;
	}

	public PersistentMap<String, VBox> alloc(PersistentMap<String, VBox> venv,
			String x) {
		return venv.plus(x, new VBox(failCons));
	}

	public PersistentMap<Object, Object> allocs(
			PersistentMap<Object, Object> venv, INodeList<String> xs) {
		for (String x : xs) {
			venv = venv.plus(x, new VBox(failCons));
		}
		return venv;
	}

	public PersistentMap<Object, Object> allocupdate(
			PersistentMap<Object, Object> venv, String x, IValue v) {
		return venv.plus(x, new VBox(v));
	}

	public PersistentMap<Object, Object> update(
			PersistentMap<Object, Object> venv, String x, IValue v) {
		VBox box = (VBox) venv.get(x);
		box.value = v;
		return venv;
	}

}

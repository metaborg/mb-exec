package ds.manual.interpreter;

import org.metaborg.meta.interpreter.framework.AValue;
import org.metaborg.meta.interpreter.framework.INodeList;

import com.github.krukow.clj_ds.PersistentMap;

import ds.generated.interpreter.F_0;

public class VState {

	private static F_0 failCons = new F_0();

	public AValue lookup(PersistentMap<Object, Object> venv, String x) {
		return ((VBox) venv.get(x)).value;
	}

	public PersistentMap<Object, Object> alloc(
			PersistentMap<Object, Object> venv, String x) {
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
			PersistentMap<Object, Object> venv, String x, AValue v) {
		return venv.plus(x, new VBox(v));
	}

	public PersistentMap<Object, Object> update(
			PersistentMap<Object, Object> venv, String x, AValue v) {
		VBox box = (VBox) venv.get(x);
		box.value = v;
		return venv;
	}

}

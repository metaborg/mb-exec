package ds.manual.interpreter;

import org.metaborg.meta.interpreter.framework.L_String;

import com.github.krukow.clj_ds.PersistentMap;

import ds.generated.interpreter.A_Value;
import ds.generated.interpreter.F_0;

public class VState {

	private static F_0 failCons = new F_0(null);

	public A_Value lookup(PersistentMap<String, VBox> venv, String x) {
		return ((VBox) venv.get(x)).value;
	}

	public PersistentMap<String, VBox> alloc(PersistentMap<String, VBox> venv,
			String x) {
		return venv.plus(x, new VBox(failCons));
	}

	public PersistentMap<String, VBox> allocs(PersistentMap<String, VBox> venv,
			L_String xs) {
		L_String l = xs;

		while (!l.isEmpty()) {
			venv = venv.plus(l.head(), new VBox(failCons));
			l = l.tail();
		}

		return venv;
	}

	public PersistentMap<String, VBox> allocupdate(
			PersistentMap<String, VBox> venv, String x, A_Value v) {
		return venv.plus(x, new VBox(v));
	}

	public PersistentMap<String, VBox> update(PersistentMap<String, VBox> venv,
			String x, A_Value v) {
		VBox box = (VBox) venv.get(x);
		box.value = v;
		return venv;
	}

}

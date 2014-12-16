package ds.manual.interpreter;

import com.github.krukow.clj_ds.PersistentMap;

import ds.generated.interpreter.I_Thunk;

public class SState {

	public PersistentMap<String, SBox> softalloc(
			PersistentMap<String, SBox> senv, String name) {
		if (senv.containsKey(name)) {
			return senv;
		}
		return senv.plus(name, new SBox());
	}

	public PersistentMap<String, SBox> alloc(PersistentMap<String, SBox> senv,
			String name) {
		return senv.plus(name, new SBox());
	}

	public PersistentMap<String, SBox> allocupdate(
			PersistentMap<String, SBox> senv, String name, I_Thunk thunk) {
		return senv.plus(name, new SBox(thunk));
	}

	public I_Thunk lookup(PersistentMap<String, SBox> senv, String name) {
		return ((SBox) senv.get(name)).thunk;
	}

	public PersistentMap<String, SBox> update(PersistentMap<String, SBox> senv,
			String name, I_Thunk thunk) {
		SBox box = (SBox) senv.get(name);
		box.thunk = thunk;
		return senv;
	}

}

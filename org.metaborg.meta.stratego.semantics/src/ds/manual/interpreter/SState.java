package ds.manual.interpreter;

import com.github.krukow.clj_ds.PersistentMap;

import ds.generated.interpreter.I_Thunk;

public class SState {

	public PersistentMap<Object, Object> alloc(
			PersistentMap<Object, Object> senv, String name) {
		return senv.plus(name, new SBox());
	}

	public PersistentMap<Object, Object> allocupdate(
			PersistentMap<Object, Object> senv, String name, I_Thunk thunk) {
		return senv.plus(name, new SBox(thunk));
	}

	public I_Thunk lookup(PersistentMap<Object, Object> senv, String name) {
		return ((SBox) senv.get(name)).thunk;
	}

	public PersistentMap<Object, Object> update(
			PersistentMap<Object, Object> senv, String name, I_Thunk thunk) {
		SBox box = (SBox) senv.get(name);
		box.thunk = thunk;
		return senv;
	}

}

package ds.manual.interpreter;

import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.stratego.StupidFormatter;

import com.github.krukow.clj_ds.PersistentMap;

import ds.generated.interpreter.F_0;
import ds.generated.interpreter.I_Strategy;
import ds.generated.interpreter.S_1;
import ds.generated.interpreter.Thunk_6;
import ds.generated.interpreter.default_Result;

public class InteropStrategy extends Strategy {

	private Thunk_6 thunk;
	private SState sheap;
	private VState vheap;

	public InteropStrategy(Thunk_6 thunk, SState sheap, VState vheap) {
		this.thunk = thunk;
		this.sheap = sheap;
		this.vheap = vheap;
	}

	@Override
	public IConstruct eval(IContext e) throws InterpreterException {
		I_Strategy s = thunk._4;
		PersistentMap<Object, Object> venv = thunk._5;
		PersistentMap<Object, Object> senv = thunk._6;

		default_Result result = s.exec_default(venv, senv,
				(AutoInterpInteropContext) e, e.getFactory(), e.current(),
				sheap, false, vheap);

		if (result.value instanceof F_0) {
			return getHook().pop().onFailure(e);
		} else {
			e.setCurrent(((S_1) result.value)._1);
			return getHook().pop().onSuccess(e);
		}
	}

	@Override
	public void prettyPrint(StupidFormatter fmt) {
		// TODO Auto-generated method stub

	}

}

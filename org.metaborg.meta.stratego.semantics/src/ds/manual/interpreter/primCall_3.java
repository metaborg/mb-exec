package ds.manual.interpreter;

import org.metaborg.meta.interpreter.framework.Child;
import org.metaborg.meta.interpreter.framework.Children;
import org.metaborg.meta.interpreter.framework.INodeList;
import org.metaborg.meta.interpreter.framework.INodeSource;
import org.metaborg.meta.interpreter.framework.InterpreterExitException;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.InterpreterExit;
import org.spoofax.interpreter.core.StackTracer;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

import com.github.krukow.clj_ds.PersistentMap;

import ds.generated.interpreter.F_0;
import ds.generated.interpreter.I_Thunk;
import ds.generated.interpreter.NoOpNode;
import ds.generated.interpreter.S_1;
import ds.generated.interpreter.Thunk_6;
import ds.generated.interpreter.default_Result;

public class primCall_3 extends NoOpNode {

	@Child
	public String name;

	@Children
	public INodeList<I_Thunk> thunks;

	@Children
	public INodeList<IStrategoTerm> targs;

	public primCall_3(INodeSource sourceInfo, String sname,
			INodeList<I_Thunk> thunks, INodeList<IStrategoTerm> ats_) {
		this.setSourceInfo(sourceInfo);
		this.name = sname;
		this.thunks = adoptChild(thunks);
		this.targs = ats_;
	}

	@Override
	public default_Result exec_default(PersistentMap<Object, Object> _1,
			PersistentMap<Object, Object> _2, AutoInterpInteropContext ctx,
			ITermFactory _4, IStrategoTerm ct, StackTracer stacktracer,
			SState sheap, boolean _7, VState vheap) {
		AbstractPrimitive prim = ctx.lookupOperator(name);
		ctx.setCurrent(ct);
		Strategy[] sargs = new Strategy[thunks.size()];

		int i = 0;
		for (I_Thunk thunk : thunks) {
			sargs[i] = new InteropStrategy((Thunk_6) thunk, stacktracer, sheap,
					vheap);
			i++;
		}
		try {
			boolean result = prim
					.call(ctx, sargs, Natives.List2TARRAY_1(targs));
			if (result) {
				return new default_Result(new S_1(ctx.current()), stacktracer,
						sheap, _7, vheap);
			} else {
				return new default_Result(new F_0(), stacktracer, sheap, _7,
						vheap);
			}
		} catch (InterpreterExit e) {
			throw new InterpreterExitException("Exit ... ");
		} catch (InterpreterException e) {
			throw new org.metaborg.meta.interpreter.framework.InterpreterException(
					"Primitive application failed", e);
		}

	}
}

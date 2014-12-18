package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.AbstractNode;
import org.metaborg.meta.interpreter.framework.Child;
import org.metaborg.meta.interpreter.framework.Children;
import org.metaborg.meta.interpreter.framework.INodeList;
import org.metaborg.meta.interpreter.framework.INodeSource;
import org.metaborg.meta.interpreter.framework.IValue;
import org.metaborg.meta.interpreter.framework.InterpreterException;
import org.metaborg.meta.interpreter.framework.InterpreterExitException;
import org.spoofax.interpreter.core.InterpreterExit;
import org.spoofax.interpreter.core.StackTracer;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

import com.github.krukow.clj_ds.PersistentMap;

import ds.manual.interpreter.AutoInterpInteropContext;
import ds.manual.interpreter.InteropStrategy;
import ds.manual.interpreter.Natives;
import ds.manual.interpreter.SBox;
import ds.manual.interpreter.SState;
import ds.manual.interpreter.VBox;
import ds.manual.interpreter.VState;

public class primCall_3 extends AbstractNode implements I_Strategy {

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
		this.thunks = adoptChildren(thunks);
		this.targs = ats_;
	}

	@Override
	public void specializeChildren(int depth) {
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public R_default_Value exec_default(PersistentMap<String, VBox> _3,
			ITermFactory _5, IStrategoTerm ct, PersistentMap<String, SBox> _2,
			AutoInterpInteropContext ctx, VState vheap,
			StackTracer stacktracer, SState sheap, boolean succ) {
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
				return new R_default_Value(new S_1(null, ctx.current()), vheap,
						stacktracer, sheap, succ);
			} else {
				return new R_default_Value(new F_0(null), vheap, stacktracer,
						sheap, succ);
			}
		} catch (InterpreterExit e) {
			throw new InterpreterExitException(e, e.getValue());
		} catch (org.spoofax.interpreter.core.InterpreterException e) {
			throw new InterpreterException(e);
		}
	}

}

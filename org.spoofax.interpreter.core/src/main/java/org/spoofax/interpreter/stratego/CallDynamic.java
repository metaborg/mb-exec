package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.util.TermUtils;

/**
 * Performs a dynamic call to a strategy. Evaluation is done by computing the
 * strategy name and rewriting to an explicit CallT which is immediately
 * evaluated.
 * 
 * @author vladvergu
 * 
 */
public class CallDynamic extends Strategy {

	private final IStrategoTerm sref;
	private final Strategy[] svars;
	private final IStrategoTerm[] tvars;

	public CallDynamic(IStrategoTerm sref, Strategy[] svars,
			IStrategoTerm[] tvars) {
		this.sref = sref;
		this.svars = svars;
		this.tvars = tvars;
	}

	@Override
	public IConstruct eval(final IContext env) throws InterpreterException {

		debug("CallDynamic.eval() - ", env.current());

		if (TermUtils.isAppl(sref) && Tools.isVar((IStrategoAppl) sref, env)) {
			IStrategoTerm actualSRef = env.lookupVar(TermUtils.toJavaStringAt(
					(IStrategoAppl) sref, 0));
			if (TermUtils.isString(actualSRef)) {
				String sname = TermUtils.toJavaString(actualSRef);
				Strategy callt = new CallT(sname, svars, tvars);
				callt.getHook().push(this.getHook().pop());
				return callt.eval(env);
			}
		}
		throw new InterpreterException(
				"Invocation target is invalid (cannot be evaluated): " + sref);

	}

	@Override
	public void prettyPrint(StupidFormatter fmt) {
		fmt.append("call-dynamic");
	}

}

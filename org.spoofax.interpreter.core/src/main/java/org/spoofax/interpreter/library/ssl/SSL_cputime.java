package org.spoofax.interpreter.library.ssl;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_cputime extends AbstractPrimitive {
	private final ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
	private final boolean canLogCPUTime;

	public SSL_cputime() {
		super("SSL_cputime", 0, 0);
		canLogCPUTime = mxBean.isThreadCpuTimeSupported();
		if(canLogCPUTime)
			mxBean.setThreadCpuTimeEnabled(true);
	}

	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) throws InterpreterException {
		if(!canLogCPUTime)
			return false;
		env.setCurrent(env.getFactory().makeReal(mxBean.getCurrentThreadCpuTime()));
		return true;
	}
}

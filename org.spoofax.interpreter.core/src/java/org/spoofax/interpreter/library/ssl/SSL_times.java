package org.spoofax.interpreter.library.ssl;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

/**
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class SSL_times extends AbstractPrimitive {

    protected SSL_times() {
        super("SSL_times", 0, 0);
    }
    
    /**
     * Returns times for self and children:
     * (utime, stime, cutime, cstime)
     */
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        ITermFactory factory = env.getFactory();
        
        // TODO: Higher precision clock? See SSL_TicksToSeconds
        
        int utime = (int) (getUserTime() / 1000000000);
        int stime = (int) (getSystemTime() / 1000000000);
        
        IStrategoTerm utimeTerm = factory.makeInt(utime);
        IStrategoTerm stimeTerm = factory.makeInt(stime);
        
        // TODO: Perhaps collect child process times?
        IStrategoTerm ctimeTerm = factory.makeInt(0);
        
        env.setCurrent(factory.makeTuple(utimeTerm, stimeTerm, ctimeTerm, ctimeTerm));
        
        return true;
    }
    
    private long getUserTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported()
            ? bean.getCurrentThreadUserTime()
            : 0L;
    }

    /** Get system time in nanoseconds. */
    private long getSystemTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported()
            ? (bean.getCurrentThreadCpuTime() - bean.getCurrentThreadUserTime())
            : 0L;
    }
}

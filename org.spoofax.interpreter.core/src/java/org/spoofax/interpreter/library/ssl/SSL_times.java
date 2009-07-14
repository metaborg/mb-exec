package org.spoofax.interpreter.library.ssl;

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
    
    public static final int TICKS_PER_SECOND = 100;
    
    private final long startTime = System.nanoTime();

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
        
        int utime = (int) (getUserTime() * TICKS_PER_SECOND / 1000000000);
        int stime = (int) (getSystemTime() * TICKS_PER_SECOND / 1000000000);
        
        IStrategoTerm utimeTerm = factory.makeInt(utime);
        IStrategoTerm stimeTerm = factory.makeInt(stime);
        IStrategoTerm ctimeTerm = factory.makeInt(0);
        
        env.setCurrent(factory.makeTuple(utimeTerm, stimeTerm, ctimeTerm, ctimeTerm));
        
        return true;
    }
    
    /**
     * Get the user time in nanoseconds.
     */
    private long getUserTime() {
        return System.nanoTime() - startTime;
        /* imprecise, incomplete MXBean method:
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported()
            ? bean.getCurrentThreadUserTime()
            : 0;
        */
    }

    /**
     * Get the system time in nanoseconds.
     */
    private long getSystemTime() {
        return 0;
        /* imprecise, incomplete MXBean method:
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported()
            ? (bean.getCurrentThreadCpuTime() - bean.getCurrentThreadUserTime())
            : 0;
        */
    }
}

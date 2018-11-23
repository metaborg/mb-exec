package org.metaborg.util.time;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * Simple time measurement functionality.
 */
public class AggregateTimer {
    /** ThreadMXBean for measuring CPU time. **/
    private final ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();

    /** If precise CPU time measurements are available. **/
    private final boolean canLogCPUTime;

    /** Last starting time since start was called. **/
    private long startTime = -1;
    private long totalTime = 0;


    public AggregateTimer() {
        this(false);
    }

    public AggregateTimer(boolean start) {
        canLogCPUTime = mxBean.isThreadCpuTimeSupported();
        if(canLogCPUTime)
            mxBean.setThreadCpuTimeEnabled(true);
        if(start)
            start();
    }


    /**
     * Starts the timer, noting the current time.
     */
    public void start() {
        if (startTime != -1) {
            throw new IllegalStateException("Timer already started.");
        }
        startTime = time();
    }

    /**
     * @return The duration, in nanoseconds, between the call to {@link #start()} and this invocation.
     */
    public long stop() {
        if (startTime == -1) {
            throw new IllegalStateException("Timer not started.");
        }
        long dt = time() - startTime;
        startTime = -1;
        totalTime += dt;
        return dt;
    }

    /**
     * @return Total time the timer was running.
     */
    public long total() {
        return totalTime;
    }
    
    /**
     * Resets the timer, forgetting the time noted when {@link #start()} was called.
     */
    public void reset() {
        startTime = -1;
        totalTime = 0;
    }


    private long time() {
        if(canLogCPUTime)
            return mxBean.getCurrentThreadCpuTime();
        else
            return System.nanoTime();
    }
}

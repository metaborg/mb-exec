package org.metaborg.util.time;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * Simple time measurement functionality.
 */
public class Timer {
    /** ThreadMXBean for measuring CPU time. **/
    private final ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();

    /** If precise CPU time measurements are available. **/
    private final boolean canLogCPUTime;

    /** Last starting time since start was called. **/
    private long startTime = 0;


    public Timer() {
        this(false);
    }

    public Timer(boolean start) {
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
        startTime = time();
    }

    /**
     * @return The duration, in nanoseconds, between the call to {@link #start()} and this invocation. This method can
     *         be called multiple times after one {@link #start()} invocation.
     */
    public long stop() {
        return time() - startTime;
    }

    /**
     * Resets the timer, forgetting the time noted when {@link #start()} was called.
     */
    public void reset() {
        startTime = 0;
    }


    private long time() {
        if(canLogCPUTime)
            return mxBean.getCurrentThreadCpuTime();
        else
            return System.nanoTime();
    }
}

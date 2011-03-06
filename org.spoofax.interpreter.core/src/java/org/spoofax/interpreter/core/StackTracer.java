package org.spoofax.interpreter.core;

import static java.lang.Math.min;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;

import org.spoofax.interpreter.library.IOAgent;

/**
 * Stack tracing support.
 * 
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class StackTracer {
    
    private static final int MAX_REPORTED_FRAMES = 130;
    
    private static final int MAX_REPORTED_FRAMES_TAIL = 30;
    
    private IOAgent ioAgent; 
    
    private String[] frames = new String[50];
    
    private int currentDepth;
    
    private int failureDepth;
    
    public final void push(String name) {
        int depth = currentDepth++;
        if (frames.length == depth) {
            String[] oldframes = frames;
            frames = new String[frames.length * 2];
            System.arraycopy(oldframes, 0, frames, 0, oldframes.length);
        }
        frames[depth] = name;
        failureDepth = currentDepth;
    }
    
    public final void popOnFailure() {
        currentDepth--;
        // failureDepth stays the same and keeps track of this failure
    }
    
    public final void popOnSuccess() {
        failureDepth = --currentDepth;
    }
    
    public void popOnExit(boolean success) {
        currentDepth = 0;
        if (success) failureDepth = 0;
    }
    
    public IOAgent getIOAgent() {
        return ioAgent;
    }
    
    public void setIOAgent(IOAgent ioAgent) {
        this.ioAgent = ioAgent;
    }
    
    /**
     *  Returns the current stack trace depth.
     */
    public int getTraceDepth() {
        return failureDepth;
    }
    
   /**
    *  Returns the current stack trace depth.
    * 
    * @param onlyCurrent
    *            true if only the current frames on the stack should be
    *            printed, and not any failed frames.
    */
    public int getTraceDepth(boolean onlyCurrent) {
        return onlyCurrent ? currentDepth : failureDepth;
    }
    
    /**
     * Returns the current stack trace.
     */
    public String[] getTrace() {
        return getTrace(false);
    }
    
    /**
     * Returns the current stack trace.
     * 
     * @param onlyCurrent
     *            true if only the current frames on the stack should be
     *            printed, and not any failed frames.
     */
    public String[] getTrace(boolean onlyCurrent) {
        int depth = onlyCurrent ? currentDepth : failureDepth;
        String[] frames = this.frames; // avoid _some_ race conditions        
        String[] results = new String[depth];
        
        for (int i = 0; i < depth; i++)
            results[results.length - i - 1] = frames[i];
        
        return results;
    }
    
    public String getTraceString() {
        StringWriter writer = new StringWriter();
        printStackTrace(writer, false);
        return writer.toString();
    }
    
    public void setTrace(String[] trace) {
        if (trace.length == 0) { // cannot resize 0-length arrays
            currentDepth = failureDepth = 0;
            frames = new String[10]; 
        } else {
            currentDepth = min(trace.length, currentDepth);
            failureDepth = trace.length;
            frames = trace;
        }
    }
    
    /**
     * Prints the stack trace to the error output stream of the IOAgent.
     */
    public final void printStackTrace() {
        printStackTrace(false);
    }

    /**
     * Prints the stack trace to the error output stream of the IOAgent.
     * 
     * @param onlyCurrent
     *            true if only the current frames on the stack should be
     *            printed, and not any failed frames.
     */
    public final void printStackTrace(boolean onlyCurrent) {
        Writer writer = getIOAgent() == null
                ? new OutputStreamWriter(System.err)
                : getIOAgent().getWriter(IOAgent.CONST_STDERR);
        printStackTrace(writer, onlyCurrent);
    }
    
    /**
     * Prints the stack trace to the default error output.
     * 
     * @param onlyCurrent
     *            true if only the current frames on the stack should be
     *            printed, and not any failed frames.
     */
    public void printStackTrace(PrintStream stream, boolean onlyCurrent) {
        printStackTrace(new OutputStreamWriter(stream), onlyCurrent);
    }

    private void printStackTrace(Writer writer, boolean onlyCurrent) {
        try {
            int depth = onlyCurrent ? currentDepth : failureDepth;
            String[] frames = this.frames.clone(); // avoid _most_ race conditions (for UncaughtExceptionHandler)
            
            // TODO: reverse the order of this trace: latest frames should be at the end...
            for (int i = 0; i < depth; i++) {
                if (i == MAX_REPORTED_FRAMES - MAX_REPORTED_FRAMES_TAIL) {
                    writer.write("...truncated..." + "\n");
                    i = Math.max(i + 1, depth - MAX_REPORTED_FRAMES_TAIL);
                }
                writer.write("\t" + frames[i] + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            // Swallow it like we're PrintStream
        }
    }
}

package org.spoofax.interpreter.core;

/**
 * Stack tracing support.
 * 
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class StackTracer {
    
    private static final int MAX_REPORTED_FRAMES = 130;
    
    private static final int MAX_REPORTED_FRAMES_TAIL = 30;
    
    private String[] frames = new String[50];
    
    private int currentDepth;
    
    private int failureDepth;
    
    public void push(String name) {
        int depth = currentDepth++;
        if (frames.length == depth) {
            String[] oldframes = frames;
            frames = new String[frames.length * 2];
            System.arraycopy(oldframes, 0, frames, 0, oldframes.length);
        }
        frames[depth] = name;
        failureDepth = currentDepth;
    }
    
    public void popOnFailure() {
        currentDepth--;
        // failureDepth stays the same and keeps track of this failure
    }
    
    public void popOnSuccess() {
        failureDepth = --currentDepth;
    }
    
    public void popOnExit(boolean success) {
        currentDepth = 0;
        if (success) failureDepth = 0;
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
    
    /**
     * Prints the stack trace to the standard error output.
     */
    public void printStackTrace() {
        printStackTrace(false);
    }

    /**
     * Prints the stack trace to the standard error output.
     * 
     * @param onlyCurrent
     *            true if only the current frames on the stack should be
     *            printed, and not any failed frames.
     */
    public void printStackTrace(boolean onlyCurrent) {
        int depth = onlyCurrent ? currentDepth : failureDepth;
        String[] frames = this.frames; // avoid _some_ race conditions
        
        for (int i = 0; i < depth; i++) {
            if (i == MAX_REPORTED_FRAMES - MAX_REPORTED_FRAMES_TAIL) {
                System.err.println("...truncated...");
                i = Math.max(i + 1, depth - MAX_REPORTED_FRAMES_TAIL);
            }
            System.err.println("\t" + frames[i]);
        }
    }
}

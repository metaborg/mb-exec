package org.spoofax.interpreter.core;

/**
 * Stack tracing support.
 * 
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class StackTracer {
    
    private static final int MAX_REPORTED_FRAMES = 300;
    
    private static final int MAX_REPORTED_FRAMES_TAIL = 30;
    
    private String[] frames = new String[50];
    
    private int currentDepth;
    
    private int failureDepth;
    
    public void push(String name) {
        if (frames.length == currentDepth) {
            String[] oldframes = frames;
            frames = new String[frames.length * 2];
            System.arraycopy(oldframes, 0, frames, 0, oldframes.length);
        }
        frames[currentDepth++] = name;
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
    
    public int getTraceDepth() {
        return failureDepth;
    }
    
    public String[] getTrace() {
        String[] results = new String[failureDepth];
        for (int i = 0; i < failureDepth; i++)
            results[results.length - i - 1] = frames[i];
        return results;
    }
    
    public void printStackTrace() {
        // Copy fields to avoid race conditions in _some_ cases
        int failureDepth = this.failureDepth;
        String[] frames = this.frames; 
        
        for (int i = 0; i < failureDepth; i++) {
            if (i == MAX_REPORTED_FRAMES - MAX_REPORTED_FRAMES_TAIL) {
                System.err.println("...truncated...");
                i = Math.max(i + 1, failureDepth - MAX_REPORTED_FRAMES_TAIL);
            }
            System.err.println("\t" + frames[i]);
        }
    }
}

package org.spoofax.interpreter.core;

/**
 * Stack tracing support.
 * 
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class StackTracer {
    private String[] items = new String[50];
    
    private int currentDepth;
    
    private int failureDepth;
    
    public void push(String name) {
        if (items.length == currentDepth) {
            String[] oldItems = items;
            items = new String[items.length * 2];
            System.arraycopy(oldItems, 0, items, 0, oldItems.length);
        }
        items[currentDepth++] = name;
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
            results[results.length - i - 1] = items[i];
        return results;
    }
    
    public void printStackTrace() {
        for (int i = 0; i < failureDepth; i++) {
            System.err.println("\t" + items[i]);
        }
    }
}

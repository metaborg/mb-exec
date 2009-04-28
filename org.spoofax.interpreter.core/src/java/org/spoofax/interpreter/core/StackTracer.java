package org.spoofax.interpreter.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    
    public void popOnExit() {
        currentDepth = 0;
    }
    
    public String[] getTrace() {
        String[] results = new String[failureDepth];
        for (int i = 0; i < failureDepth; i++)
            results[results.length - i - 1] = items[i];
        return results;
    }
    
    public void printStackTrace() {
        List<String> reverseTrace = Arrays.asList(getTrace());
        Collections.reverse(reverseTrace);
        for (String s : reverseTrace) {
            System.err.println("\t" + s);
        }
    }
}

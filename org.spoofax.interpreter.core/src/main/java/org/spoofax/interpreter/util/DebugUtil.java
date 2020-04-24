package org.spoofax.interpreter.util;

import org.spoofax.interpreter.core.Context;
import org.spoofax.interpreter.stratego.CallT;
import org.spoofax.interpreter.stratego.Let;
import org.spoofax.interpreter.stratego.Scope;
import org.spoofax.interpreter.stratego.Strategy;

/*
 * Created on 28.mar.2006
 *
 * Copyright (c) 2006-2007, Karl Trygve Kalleberg <karltk near strategoxt.org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
public class DebugUtil {
    public static boolean debugging = false;
    public static boolean tracing = false;
    
    public static final int INDENT_STEP = 2;
    //public static boolean resetSSL = true;
    //public static boolean cleanupInShutdown = true;
    //public static boolean shareFactory = true;

    /**
     * Debug utility to trace the result of a completed strategy and the 'current'
     * term upon it's completion. </br>
     * We do not trace return of {@link org.spoofax.interpreter.stratego.Seq} or {@link org.spoofax.interpreter.stratego.Scope} for clarity.
     */
    public static boolean traceReturn(boolean result, Object current, final Strategy strategy) {
        if (debugging) {
            // Indent just for stragies that use a scope.
            boolean doIndent = strategy instanceof CallT || strategy instanceof Let || strategy instanceof Scope;
            String s = doIndent ? buildIndent(INDENT_STEP).toString() : "";
            if(!result) {
                Context.debug(s, "=> failed: ", current, "\n");
            } else {
                Context.debug(s, "=> succeeded: ", current, "\n");
            }
        }
        return result;
    }


    // These debug() overloads are for optimizations.
    // Prevents allocating an array for the arguments
    // in the most common cases.

    /**
     * Prints the string representation of the given object on the standard OUT
     * if debugging is enabled.
     *
     * Note: it is not needed to check {@link DebugUtil#isDebugging()} before calling this method.
     *
     * @param s0 the object to print
     */
    public static void debug(Object s0) {
        if (!DebugUtil.isDebugging()) return;
        printIfWithinLimit(s0.toString());
    }

    /**
     * Prints the string representation of the given objects on the standard OUT
     * if debugging is enabled.
     *
     * Note: it is not needed to check {@link DebugUtil#isDebugging()} before calling this method.
     *
     * @param s0 the first object to print
     * @param s1 the second object to print
     */
    public static void debug(Object s0, Object s1) {
        if (!DebugUtil.isDebugging()) return;
        StringBuilder toPrint = new StringBuilder();
        writeObject(s0, toPrint);
        writeObject(s1, toPrint);
        printIfWithinLimit(toPrint.toString());
    }

    /**
     * Prints the string representation of the given objects on the standard OUT
     * if debugging is enabled.
     *
     * Note: it is not needed to check {@link DebugUtil#isDebugging()} before calling this method.
     *
     * @param s0 the first object to print
     * @param s1 the second object to print
     * @param s2 the third object to print
     */
    public static void debug(Object s0, Object s1, Object s2) {
        if (!DebugUtil.isDebugging()) return;
        StringBuilder toPrint = new StringBuilder();
        writeObject(s0, toPrint);
        writeObject(s1, toPrint);
        writeObject(s2, toPrint);
        printIfWithinLimit(toPrint.toString());
    }

    /**
     * Prints the string representation of the given objects on the standard OUT
     * if debugging is enabled.
     *
     * Note: it is not needed to check {@link DebugUtil#isDebugging()} before calling this method.
     *
     * @param s0 the first object to print
     * @param s1 the second object to print
     * @param s2 the third object to print
     * @param s3 the fourth object to print
     */
    public static void debug(Object s0, Object s1, Object s2, Object s3) {
        if (!DebugUtil.isDebugging()) return;
        StringBuilder toPrint = new StringBuilder();
        writeObject(s0, toPrint);
        writeObject(s1, toPrint);
        writeObject(s2, toPrint);
        writeObject(s3, toPrint);
        printIfWithinLimit(toPrint.toString());
    }

    /**
     * Prints the string representation of the given objects on the standard OUT
     * if debugging is enabled.
     *
     * Note: it is not needed to check {@link DebugUtil#isDebugging()} before calling this method.
     *
     * @param strings the objects to print
     */
    public static void debug(Object... strings) {
        if (!DebugUtil.isDebugging()) return;
        StringBuilder toPrint = new StringBuilder();
        for (Object s : strings) {
            writeObject(s, toPrint);
        }
        printIfWithinLimit(toPrint.toString());
    }

    private static void printIfWithinLimit(String s) {
        if (s.length() >= 20000) return;
        System.out.println(s);
    }

    private static void writeObject(Object obj, StringBuilder sb) {
        if(obj.getClass().isArray()) {
            Object[] ss = (Object[])obj;
            for (Object o : ss) {
                sb.append(o);
            }
        } else {
            sb.append(obj); //pay the price
        }
    }

    public static void setDebug(boolean b) {
        debugging = b;
    }

    public static void bump() {
        Context.indentation += INDENT_STEP;
    }

    public static void unbump() {
        Context.indentation -= INDENT_STEP;
    }

    private final static char[] indent = new char[2000];
    static {
        for (int i = 0; i < indent.length; i++) {
            indent[i] = ' ';
        }
    }

    public static StringBuilder buildIndent(final int indentation) {
        StringBuilder b = new StringBuilder(indentation);
        b.append(indent, 0, indentation);
        return b;
    }

    public static boolean isDebugging() {
        return debugging;
    }

    public static void setTracing(boolean enableTracing) {
        tracing = enableTracing;
    }
}

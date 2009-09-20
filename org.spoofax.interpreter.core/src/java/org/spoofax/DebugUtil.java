package org.spoofax;

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
 * Licensed under the GNU General Public License, v2
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
                Strategy.debug(s, "=> failed: ", current, "\n");
            } else {
                Strategy.debug(s, "=> succeeded: ", current, "\n");
            }
        }
        return result;
    }

    public static void debug(Object... strings) {

        String toPrint = "";
        if (strings.length > 1) {
            for (Object s : strings) {
                if(s.getClass().isArray()) {
                    Object ss[] = (Object[])s;
                    for (Object o : ss) {
                        toPrint += o;
                    }
                } else {
                    toPrint += s; //pay the price
                }
            }
        }
        else {
            toPrint = (strings[0]).toString();
        }
        if (toPrint.length() < 20000) {
            System.out.println(toPrint);
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

/*
 * Created on 04.jul.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 *
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

public class InterpreterException extends Exception {

    private static final long serialVersionUID = -3622131518420023392L;
    private String error;
    private Exception nested;

    public InterpreterException(String s) {
        this.error = s;
    }

    public InterpreterException(Exception nested) {
        this.nested = nested;
    }

    public String toString() {
        if(error != null)
            return "InterpreterException: " + error;
        else
            return "InterpreterException: " + nested.toString();
    }

    public String getReason() {
        return error;
    }

    public Exception getNestedException() {
        return nested;
    }
}

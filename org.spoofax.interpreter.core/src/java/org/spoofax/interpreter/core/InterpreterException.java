/*
 * Created on 04.jul.2005
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.core;

public class InterpreterException extends Exception {

    private static final long serialVersionUID = -3622131518420023392L;

    public InterpreterException(String message, Throwable cause) {
        super(message, cause);
    }

    public InterpreterException(String message) {
        super(message);
    }

    public InterpreterException(Exception cause) {
        super(cause);
    }
}

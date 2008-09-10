/*
 * Created on 04.jul.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 *
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.core;

public class InterpreterException extends Exception {

    private static final long serialVersionUID = -3622131518420023392L;
    
    public InterpreterException(String message, Exception cause) {
        super(message, cause);
    }

    public InterpreterException(String message) {
        super(message);
    }

    public InterpreterException(Exception cause) {
        super(cause);
    }
}

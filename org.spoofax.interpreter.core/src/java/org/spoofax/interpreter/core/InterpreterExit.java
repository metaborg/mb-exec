/*
 * Created on 15. jan.. 2007
 *
 * Copyright (c) 2007-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.core;

public class InterpreterExit extends InterpreterException {

    private static final long serialVersionUID = 7198021396619788526L;

    public static final int SUCCESS = 0;

    private final int value;

    public InterpreterExit(int value) {
        this(value, (Throwable) null);
    }

    public InterpreterExit(int value, Throwable cause) {
        super("Legal exit: return code " + value, cause);
        this.value = value;
    }

    public InterpreterExit(int value, String message) {
        super(message);
        this.value = value;
    }

    public InterpreterExit(int value, String message, Throwable cause) {
        super(message, cause);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}

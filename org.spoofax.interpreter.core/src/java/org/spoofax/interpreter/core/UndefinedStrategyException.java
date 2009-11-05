package org.spoofax.interpreter.core;

/**
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class UndefinedStrategyException extends InterpreterException {

    private static final long serialVersionUID = 6886950665182075267L;

    public UndefinedStrategyException(String message, Throwable cause) {
        super(message, cause);
    }

    public UndefinedStrategyException(String message) {
        super(message);
    }

    public UndefinedStrategyException(Exception cause) {
        super(cause);
    }

}

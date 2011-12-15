/*
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.core;

/**
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class UndefinedStrategyException extends InterpreterException {

    private static final long serialVersionUID = 6886950665182075268L;
    private final String strategyName;

    public UndefinedStrategyException(String message, String strategyName) {
        super(message);
        this.strategyName = strategyName;
    }

    public UndefinedStrategyException(Exception cause, String strategyName) {
        super(cause);
        this.strategyName = strategyName;
    }

    public String getStrategyName() {
        return strategyName;
    }

}

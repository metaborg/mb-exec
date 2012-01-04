/*
 * Licensed under the GNU Lesser Lesser General Public License, v2.1.1
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

    public UndefinedStrategyException(Exception cause) {
        super(cause);
        this.strategyName = null;
    }

    /**
     * Gets the strategy name that was attempted to be called,
     * or <code>null</code> if the name was to be determined dynamically
     * (e.g., by {@link org.strategoxt.lang.compat.SSL_EXT_java_call})
     */
    public String getStrategyName() {
        return strategyName;
    }

}

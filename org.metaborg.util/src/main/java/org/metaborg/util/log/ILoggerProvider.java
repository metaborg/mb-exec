package org.metaborg.util.log;

/**
 * Provider for loggers.
 */
public interface ILoggerProvider {

    /**
     * Gets a logger for the specified class.
     *
     * This may or may not reuse an existing logger for the given class.
     *
     * @param cls the class for which to get the logger
     * @return the logger
     */
    ILogger getLogger(Class<?> cls);

    /**
     * Gets a logger with the specified name.
     *
     * This may or may not reuse an existing logger with the specified name.
     *
     * @param name the name of the logger
     * @return the logger
     */
    ILogger getLogger(String name);

}

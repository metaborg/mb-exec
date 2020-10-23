package org.metaborg.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logger provider that uses SLF4J.
 */
public final class Slf4jLoggerProvider implements ILoggerProvider {

    @Override
    public ILogger getLogger(Class<?> cls) {
        return getLogger(LoggerFactory.getLogger(cls));
    }

    @Override
    public ILogger getLogger(String name) {
        return getLogger(LoggerFactory.getLogger(name));
    }

    /**
     * Gets a logger wrapping the specified SLF4J logger.
     *
     * @param slf4jLogger the SLF4J logger to wrap
     * @return the logger
     */
    public ILogger getLogger(Logger slf4jLogger) {
        return new MetaborgLogger(slf4jLogger);
    }
}

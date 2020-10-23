package org.metaborg.util.log;

import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class LoggerUtils {

    private static final String CONTEXT_ID = "contextid";

    private static final Slf4jLoggerProvider loggerProvider = new Slf4jLoggerProvider();

    /**
     * Gets the default logger provider.
     *
     * @return the logger provider
     */
    public static ILoggerProvider getDefaultLoggerProvider() {
        return loggerProvider;
    }

    private static ILogger logger(Logger slf4jlogger) {
        return loggerProvider.getLogger(slf4jlogger);
    }

    public static ILogger logger(Class<?> clazz) {
        return loggerProvider.getLogger(clazz);
    }

    public static ILogger logger(String name) {
        return loggerProvider.getLogger(name);
    }


    public static void setContextId(String contextId) {
        MDC.put(CONTEXT_ID, contextId);
    }

    public static void clearContextId() {
        MDC.remove(CONTEXT_ID);
    }


    public static OutputStream stream(ILogger logger, Level level, String... excludePatterns) {
        return new LoggingOutputStream(logger, level, excludePatterns);
    }

    public static OutputStream stream(ILogger logger, String... excludePatterns) {
        return new LoggingOutputStream(logger, Level.Info, excludePatterns);
    }

    public static OutputStream stream(Logger slf4jlogger, Level level, String... excludePatterns) {
        return new LoggingOutputStream(logger(slf4jlogger), level, excludePatterns);
    }

    public static OutputStream stream(Logger slf4jlogger, String... excludePatterns) {
        return new LoggingOutputStream(logger(slf4jlogger), Level.Info, excludePatterns);
    }

    public static OutputStream stream(Class<?> clazz, Level level, String... excludePatterns) {
        return new LoggingOutputStream(logger(clazz), level, excludePatterns);
    }

    public static OutputStream stream(Class<?> clazz, String... excludePatterns) {
        return new LoggingOutputStream(logger(clazz), Level.Info, excludePatterns);
    }

    public static OutputStream stream(String name, Level level, String... excludePatterns) {
        return new LoggingOutputStream(logger(name), level, excludePatterns);
    }

    public static OutputStream stream(String name, String... excludePatterns) {
        return new LoggingOutputStream(logger(name), Level.Info, excludePatterns);
    }
}

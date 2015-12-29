package org.metaborg.util.log;

import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {
    public static ILogger logger(Logger slf4jlogger) {
        return new MetaborgLogger(slf4jlogger);
    }

    public static ILogger logger(Class<?> clazz) {
        return logger(LoggerFactory.getLogger(clazz));
    }

    public static ILogger logger(String name) {
        return logger(LoggerFactory.getLogger(name));
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

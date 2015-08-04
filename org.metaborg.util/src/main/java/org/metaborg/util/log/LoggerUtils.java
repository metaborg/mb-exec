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


    public static OutputStream stream(ILogger logger, Level level) {
        return new LoggingOutputStream(logger, level);
    }

    public static OutputStream stream(ILogger logger) {
        return new LoggingOutputStream(logger, Level.Info);
    }

    public static OutputStream stream(Logger slf4jlogger, Level level) {
        return new LoggingOutputStream(logger(slf4jlogger), level);
    }

    public static OutputStream stream(Logger slf4jlogger) {
        return new LoggingOutputStream(logger(slf4jlogger), Level.Info);
    }

    public static OutputStream stream(Class<?> clazz, Level level) {
        return new LoggingOutputStream(logger(clazz), level);
    }

    public static OutputStream stream(Class<?> clazz) {
        return new LoggingOutputStream(logger(clazz), Level.Info);
    }

    public static OutputStream stream(String name, Level level) {
        return new LoggingOutputStream(logger(name), level);
    }

    public static OutputStream stream(String name) {
        return new LoggingOutputStream(logger(name), Level.Info);
    }
}

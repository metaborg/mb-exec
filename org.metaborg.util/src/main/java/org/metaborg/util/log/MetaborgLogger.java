package org.metaborg.util.log;

import org.slf4j.Logger;
import org.slf4j.helpers.MessageFormatter;

public class MetaborgLogger implements ILogger {
    private final Logger logger;


    public MetaborgLogger(Logger logger) {
        this.logger = logger;
    }


    @Override public void trace(String msg) {
        logger.trace(msg);
    }

    @Override public void trace(String msg, Throwable t) {
        logger.trace(msg, t);
    }

    @Override public void trace(String fmt, Object... args) {
        logger.trace(fmt, args);
    }

    @Override public void trace(String fmt, Throwable t, Object... args) {
        if(logger.isTraceEnabled()) {
            final String msg = format(fmt, args);
            trace(msg, t);
        }
    }

    @Override public boolean traceEnabled() {
        return logger.isTraceEnabled();
    }


    @Override public void debug(String msg) {
        logger.debug(msg);
    }

    @Override public void debug(String msg, Throwable t) {
        logger.debug(msg, t);
    }

    @Override public void debug(String fmt, Object... args) {
        logger.debug(fmt, args);
    }

    @Override public void debug(String fmt, Throwable t, Object... args) {
        if(logger.isDebugEnabled()) {
            final String msg = format(fmt, args);
            debug(msg, t);
        }
    }

    @Override public boolean debugEnabled() {
        return logger.isDebugEnabled();
    }


    @Override public void info(String msg) {
        logger.info(msg);
    }

    @Override public void info(String msg, Throwable t) {
        logger.info(msg, t);
    }

    @Override public void info(String fmt, Object... args) {
        logger.info(fmt, args);
    }

    @Override public void info(String fmt, Throwable t, Object... args) {
        if(logger.isInfoEnabled()) {
            final String msg = format(fmt, args);
            info(msg, t);
        }
    }

    @Override public boolean infoEnabled() {
        return logger.isInfoEnabled();
    }


    @Override public void warn(String msg) {
        logger.warn(msg);
    }

    @Override public void warn(String msg, Throwable t) {
        logger.warn(msg, t);
    }

    @Override public void warn(String fmt, Object... args) {
        logger.warn(fmt, args);
    }

    @Override public void warn(String fmt, Throwable t, Object... args) {
        if(logger.isWarnEnabled()) {
            final String msg = format(fmt, args);
            warn(msg, t);
        }
    }

    @Override public boolean warnEnabled() {
        return logger.isWarnEnabled();
    }


    @Override public void error(String msg) {
        logger.error(msg);
    }

    @Override public void error(String msg, Throwable t) {
        logger.error(msg, t);
    }

    @Override public void error(String fmt, Object... args) {
        logger.error(fmt, args);
    }

    @Override public void error(String fmt, Throwable t, Object... args) {
        if(logger.isErrorEnabled()) {
            final String msg = format(fmt, args);
            error(msg, t);
        }
    }

    @Override public boolean errorEnabled() {
        return logger.isErrorEnabled();
    }


    @Override public void log(Level level, String msg) {
        switch(level) {
            case Trace:
                trace(msg);
                break;
            case Debug:
                debug(msg);
                break;
            case Info:
                info(msg);
                break;
            case Warn:
                warn(msg);
                break;
            case Error:
                error(msg);
                break;
        }
    }

    @Override public void log(Level level, String msg, Throwable t) {
        switch(level) {
            case Trace:
                trace(msg, t);
                break;
            case Debug:
                debug(msg, t);
                break;
            case Info:
                info(msg, t);
                break;
            case Warn:
                warn(msg, t);
                break;
            case Error:
                error(msg, t);
                break;
        }
    }

    @Override public void log(Level level, String fmt, Object... args) {
        switch(level) {
            case Trace:
                trace(fmt, args);
                break;
            case Debug:
                debug(fmt, args);
                break;
            case Info:
                info(fmt, args);
                break;
            case Warn:
                warn(fmt, args);
                break;
            case Error:
                error(fmt, args);
                break;
        }
    }

    @Override public void log(Level level, String fmt, Throwable t, Object... args) {
        switch(level) {
            case Trace:
                trace(fmt, t, args);
                break;
            case Debug:
                debug(fmt, t, args);
                break;
            case Info:
                info(fmt, t, args);
                break;
            case Warn:
                warn(fmt, t, args);
                break;
            case Error:
                error(fmt, t, args);
                break;
        }
    }

    @Override public boolean enabled(Level level) {
        switch(level) {
            case Trace:
                return traceEnabled();
            case Debug:
                return debugEnabled();
            case Info:
                return infoEnabled();
            case Warn:
                return warnEnabled();
            case Error:
                return errorEnabled();
        }
        return false;
    }


    @Override public String format(String fmt, Object... args) {
        return MessageFormatter.arrayFormat(fmt, args).getMessage();
    }
}

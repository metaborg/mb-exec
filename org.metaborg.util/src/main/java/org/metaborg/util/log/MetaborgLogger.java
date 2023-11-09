package org.metaborg.util.log;

import org.slf4j.Logger;
import org.slf4j.helpers.MessageFormatter;

import jakarta.annotation.Nullable;

public class MetaborgLogger extends AbstractLogger {
    private final Logger logger;


    public MetaborgLogger(Logger logger) {
        this.logger = logger;
    }

    @Override public void trace(String msg, @Nullable Throwable t) {
        if (t != null) {
            logger.trace(msg, t);
        } else {
            logger.trace(msg);
        }
    }

    @Override public boolean traceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override public void debug(String msg, @Nullable Throwable t) {
        if (t != null) {
            logger.debug(msg, t);
        } else {
            logger.debug(msg);
        }
    }

    @Override public boolean debugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override public void info(String msg, @Nullable Throwable t) {
        if (t != null) {
            logger.info(msg, t);
        } else {
            logger.info(msg);
        }
    }

    @Override public boolean infoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override public void warn(String msg, @Nullable Throwable t) {
        if (t != null) {
            logger.warn(msg, t);
        } else {
            logger.warn(msg);
        }
    }

    @Override public boolean warnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override public void error(String msg, @Nullable Throwable t) {
        if (t != null) {
            logger.error(msg, t);
        } else {
            logger.error(msg);
        }
    }

    @Override public boolean errorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override public String format(String fmt, Object... args) {
        return MessageFormatter.arrayFormat(fmt, args).getMessage();
    }
}

package org.metaborg.util.log;

import org.slf4j.helpers.MessageFormatter;

import jakarta.annotation.Nullable;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Logger that captures anything logged to it.
 *
 * This is used in tests.
 */
@SuppressWarnings("unused")
public final class CapturingLogger extends AbstractLogger {

    private final StringWriter captured = new StringWriter();
    private boolean traceEnabled = true;
    private boolean debugEnabled = true;
    private boolean infoEnabled = true;
    private boolean warnEnabled = true;
    private boolean errorEnabled = true;

    @Override public void trace(String msg, @Nullable Throwable cause) {
        if (!traceEnabled()) return;
        appendLog(Level.Trace, msg, cause);
    }

    @Override public boolean traceEnabled() {
        return this.traceEnabled;
    }

    /**
     * Sets whether to log trace messages.
     *
     * @param enabled {@code true} to log trace messages; otherwise, {@code false}
     */
    public void setTraceEnabled(boolean enabled) {
        this.traceEnabled = enabled;
    }

    @Override public void debug(String msg, @Nullable Throwable cause) {
        if (!debugEnabled()) return;
        appendLog(Level.Debug, msg, cause);
    }

    @Override public boolean debugEnabled() {
        return this.debugEnabled;
    }

    /**
     * Sets whether to log debug messages.
     *
     * @param enabled {@code true} to log debug messages; otherwise, {@code false}
     */
    public void setDebugEnabled(boolean enabled) {
        this.debugEnabled = enabled;
    }

    @Override public void info(String msg, @Nullable Throwable cause) {
        if (!infoEnabled()) return;
        appendLog(Level.Info, msg, cause);
    }

    @Override public boolean infoEnabled() {
        return this.infoEnabled;
    }

    /**
     * Sets whether to log info messages.
     *
     * @param enabled {@code true} to log info messages; otherwise, {@code false}
     */
    public void setInfoEnabled(boolean enabled) {
        this.infoEnabled = enabled;
    }

    @Override public void warn(String msg, @Nullable Throwable cause) {
        if (!warnEnabled()) return;
        appendLog(Level.Warn, msg, cause);
    }

    @Override public boolean warnEnabled() {
        return this.warnEnabled;
    }

    /**
     * Sets whether to log warning messages.
     *
     * @param enabled {@code true} to log warning messages; otherwise, {@code false}
     */
    public void setWarnEnabled(boolean enabled) {
        this.warnEnabled = enabled;
    }

    @Override public void error(String msg, @Nullable Throwable cause) {
        if (!errorEnabled()) return;
        appendLog(Level.Error, msg, cause);
    }

    @Override public boolean errorEnabled() {
        return this.errorEnabled;
    }

    /**
     * Sets whether to log error messages.
     *
     * @param enabled {@code true} to log error messages; otherwise, {@code false}
     */
    public void setErrorEnabled(boolean enabled) {
        this.errorEnabled = enabled;
    }

    @Override public String format(String msg, Object... args) {
        return MessageFormatter.arrayFormat(msg, args).getMessage();
    }

    /**
     * Returns the captured output as a string.
     *
     * @return the captured output
     */
    @Override
    public String toString() {
        return this.captured.toString();
    }

    /**
     * Appends a message to the log.
     *
     * @param level the log level
     * @param msg the message
     * @param cause the throwable; or {@code null}
     */
    private void appendLog(Level level, String msg, @Nullable Throwable cause) {
        this.captured.append("[").append(levelToString(level)).append("] ")
                .append(msg).append("\n");
        if (cause != null) {
            cause.printStackTrace(new PrintWriter(this.captured));
        }
    }

    /**
     * Returns a string representation of the given log level.
     *
     * @param level the log level
     * @return the string representation
     */
    private String levelToString(Level level) {
        switch (level) {
            case Trace: return "TRACE";
            case Debug: return "DEBUG";
            case Info: return "INFO ";
            case Warn: return "WARN ";
            case Error: return "ERROR";
            default: return "?????";
        }
    }
}

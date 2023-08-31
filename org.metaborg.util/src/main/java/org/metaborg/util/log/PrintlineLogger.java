package org.metaborg.util.log;

/**
 * @deprecated Temporary device for debugging on buildfarm.
 */
@Deprecated
public class PrintlineLogger {

    private static final ILogger log = LoggerUtils.logger(PrintlineLogger.class);

    private static volatile boolean globalEnabled = false;

    private final String name;
    private final boolean enabled;

    public PrintlineLogger(String name) {
        this.name = truncate(name);
        enabled = !name.startsWith("mb.nabl2") && !name.startsWith("mb.scopegraph");
    }

    public void debug(String message, Object... args) {
        log(format(message, args), "[DEBUG] -");
    }

    public void debug(String message, Throwable ex, Object... args) {
        log(format(message, args), "[DEBUG] -", ex);
    }

    public void info(String message, Object... args) {
        log(format(message, args), "[INFO]  -");
    }

    public void info(String message, Throwable ex, Object... args) {
        log(format(message, args), "[INFO]  -", ex);
    }

    public void warn(String message, Object... args) {
        log(format(message, args), "[WARN]  -");
    }

    public void warn(String message, Throwable ex, Object... args) {
        log(format(message, args), "[WARN]  -", ex);
    }

    public void error(String message, Object... args) {
        log(format(message, args), "[ERROR] -");
    }

    public void error(String message, Throwable ex, Object... args) {
        log(format(message, args), "[ERROR] -", ex);
    }

    private String format(String format, Object... args) {
        return log.format(format, args);
    }

    private void log(String message, String level) {
        if(enabled()) {
            System.out.printf("%s %-32s | %s%n", level, name, message);
        }
    }

    private void log(String message, String level, Throwable ex) {
        if(enabled()) {
            System.out.printf("%s %-32s | %s%n", level, name, message);
            ex.printStackTrace(System.out);
        }
    }

    private boolean enabled() {
        return enabled && globalEnabled;
    }

    private String truncate(String name) {
        if(name.length() > 32) {
            return name.substring(name.length() - 32);
        } else {
            return name;
        }
    }

    public static PrintlineLogger logger(Class<?> clz) {
        return new PrintlineLogger(clz.getCanonicalName());
    }

    public static void enableGlobal() {
        globalEnabled = true;
    }

    public static void disableGlobal() {
        globalEnabled = false;
    }

}


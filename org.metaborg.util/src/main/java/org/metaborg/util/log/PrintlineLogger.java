package org.metaborg.util.log;

/**
 * @deprecated Temporary device for debugging on buildfarm.
 */
@Deprecated
public class PrintlineLogger {

    private static final ILogger log = LoggerUtils.logger(PrintlineLogger.class);

    private static boolean globalEnabled = false;

    private final String name;
    private final boolean enabled;

    public PrintlineLogger(String name) {
        this.name = truncate(name);
        enabled = name.endsWith("ISolver")
                || name.endsWith("EqualityComponent")
                || name.endsWith("SemiIncrementalMultiFileSolver")
                || name.startsWith("mb.nabl2.spoofax.primitives")
                || name.startsWith("org.metaborg.spoofax.core.analysis.constraint")
                || name.startsWith("org.metaborg.spt.core.run.expectations");
    }

    public void debug(String format, Object... args) {
        log(format, "[DEBUG] -", args);
    }

    public void debug(String format, Throwable ex, Object... args) {
        log(format, "[DEBUG] -", ex, args);
    }

    public void info(String format, Object... args) {
        log(format, "[INFO]  -", args);
    }

    public void info(String format, Throwable ex, Object... args) {
        log(format, "[INFO]  -", ex, args);
    }

    public void warn(String format, Object... args) {
        log(format, "[WARN]  -", args);
    }

    public void warn(String format, Throwable ex, Object... args) {
        log(format, "[WARN]  -", ex, args);
    }

    public void error(String format, Object... args) {
        log(format, "[ERROR] -", args);
    }

    public void error(String format, Throwable ex, Object... args) {
        log(format, "[ERROR] -", ex, args);
    }

    private void log(String message, String level) {
        if(enabled()) {
            System.out.printf("%s %-32s | %s%n", level, name, message);
        }
    }

    private void log(String format, String level, Object... args) {
        if(enabled()) {
            System.out.printf("%s %-32s | %s%n", level, name, log.format(format, args));
        }
    }

    private void log(String format, String level, Throwable ex, Object... args) {
        if(enabled()) {
            System.out.printf("%s %-32s | %s%n", level, name, log.format(format, args));
            ex.printStackTrace(System.out);
        }
    }

    public boolean enabled() {
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
	// disabled for the release
	// globalEnabled = true;
    }

    public static void disableGlobal() {
        globalEnabled = false;
    }

}


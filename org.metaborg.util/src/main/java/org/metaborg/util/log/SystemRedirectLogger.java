package org.metaborg.util.log;

import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Redirects System.out and System.err to SLF4J. Code from {@link http://stackoverflow.com/a/11187462/499240}.
 */
public class SystemRedirectLogger {
    private static final Logger stdoutLogger = LoggerFactory.getLogger("stdout");
    private static final Logger stderrLogger = LoggerFactory.getLogger("stderr");
    private static final PrintStream stdout = System.out;
    private static final PrintStream stderr = System.err;


    public static void redirect() {
        System.setOut(new PrintStream(LoggerUtils.stream(stdoutLogger, Level.Info), true));
        System.setErr(new PrintStream(LoggerUtils.stream(stderrLogger, Level.Error), true));
    }

    public static void unredirect() {
        System.setOut(stdout);
        System.setErr(stderr);
    }
}

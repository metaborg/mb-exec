package org.metaborg.util.log;

import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Redirects System.out and System.err to SLF4J. Code from {@link http://stackoverflow.com/a/11187462/499240}.
 */
public class SystemRedirectLogger {
    private static final String lineSeperator = System.getProperty("line.separator");
    static final char firstLineSeparator = lineSeperator.charAt(0);
    static final char lastLineSeparator = lineSeperator.charAt(lineSeperator.length() - 1);
    private static final Logger stdoutLogger = LoggerFactory.getLogger("stdout");
    private static final Logger stderrLogger = LoggerFactory.getLogger("stderr");
    private static final PrintStream stdout = System.out;
    private static final PrintStream stderr = System.err;


    public static void redirect() {
        System.setOut(new PrintStream(new LoggingOutputStream(stdoutLogger, false), true));
        System.setErr(new PrintStream(new LoggingOutputStream(stderrLogger, true), true));
    }

    public static void unredirect() {
        System.setOut(stdout);
        System.setErr(stderr);
    }
}
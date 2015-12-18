package org.metaborg.util.log;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Redirects System.out and System.err to SLF4J. Code from {@link http://stackoverflow.com/a/11187462/499240}.
 */
public class SystemRedirectLogger {
    private static final String lineSeperator = System.getProperty("line.separator");
    private static final char firstLineSeparator = lineSeperator.charAt(0);
    private static final char lastLineSeparator = lineSeperator.charAt(lineSeperator.length() - 1);
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


    private static class LoggingOutputStream extends OutputStream {
        private static final int initialBufferLength = 2048;

        private final Logger logger;
        private final boolean logAsError;
        private boolean flush = false;
        private boolean closed = false;
        private byte[] buffer;
        private int count;


        public LoggingOutputStream(Logger logger, boolean logAsError) throws IllegalArgumentException {
            this.logAsError = logAsError;
            this.logger = logger;

            buffer = new byte[initialBufferLength];
            count = 0;
        }


        @Override public void close() {
            flush();
            closed = true;
        }

        @Override public void write(final int b) throws IOException {
            if(closed) {
                throw new IOException("The stream has been closed.");
            }

            if(b == 0) {
                // Do not log nulls.
                return;
            } else if(b == lastLineSeparator) {
                // Enable flushing if writing last line separator.
                flush = true;
                return;
            } else if(b == firstLineSeparator) {
                // Ignore first line separator. Flushing already enabled if lastLineSeparator == firstLineSeparator.
                return;
            }

            // Grow buffer if it is full.
            if(count == buffer.length) {
                final int newBufLength = buffer.length + initialBufferLength;
                final byte[] newBuf = new byte[newBufLength];
                System.arraycopy(buffer, 0, newBuf, 0, buffer.length);
                buffer = newBuf;
            }

            buffer[count] = (byte) b;
            count++;
        }

        @Override public void flush() {
            if(count == 0 || !flush) {
                return;
            }

            final byte[] bytes = new byte[count];
            System.arraycopy(buffer, 0, bytes, 0, count);
            if(logAsError) {
                logger.error(new String(bytes));
            } else {
                logger.info(new String(bytes));
            }

            // Not resetting the buffer; assuming that if it grew that it will likely grow similarly again.
            count = 0;
            flush = false;
        }
    }
}
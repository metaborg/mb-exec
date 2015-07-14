package org.metaborg.util.log;

import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;

public class LoggingOutputStream extends OutputStream {
    private static final int initialBufferLength = 2048;

    private final Logger logger;
    private final boolean logAsError;
    private boolean closed = false;
    private boolean forceFlush = false;
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

        switch(b) {
            case '\n':
                // Flush if writing last line separator.
                forceFlush = true;
                flush();
                return;
            case 0:
                // Do not log nulls.
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
        if(!forceFlush && count == 0) {
            return;
        }

        final String message = new String(buffer, 0, count);
        if(logAsError) {
            logger.error(message);
        } else {
            logger.info(message);
        }

        // Not resetting the buffer; assuming that if it grew that it will likely grow similarly again.
        count = 0;
        forceFlush = false;
    }
}
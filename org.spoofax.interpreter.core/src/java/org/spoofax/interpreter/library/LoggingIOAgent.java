package org.spoofax.interpreter.library;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * An IO Agent class that logs all console output.
 * 
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class LoggingIOAgent extends IOAgent {
    private final PrintStream stderr =
        new PrintStream(new LoggingOutputStream(System.err), true); 
    private final PrintStream stdout =
        new PrintStream(new LoggingOutputStream(System.out), true);
    private final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    
    public String getLog() {
    	return bytes.toString();
    }
    
    public void clearLog() {
        bytes.reset();
    }
    
    @Override
    public PrintStream getOutputStream(int fd) {
        switch (fd) {
            case CONST_STDERR:
                return stderr;
            case CONST_STDOUT:
                return stdout;
            default:
                return super.getOutputStream(fd);
        }
    }
    
    private class LoggingOutputStream extends OutputStream {
        private final OutputStream stream;
        
        public LoggingOutputStream(OutputStream stream) {
            this.stream = stream;
        }
        
        @Override
        public void write(int b) throws IOException {
            stream.write(b);
            bytes.write(b); 
        }
        
        @Override
        public void write(byte[] b) throws IOException {
            stream.write(b);
            bytes.write(b);
        }
        
        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            stream.write(b, off, len);
            bytes.write(b, off, len);
        }
        
        @Override
        public void flush() throws IOException {
            stream.flush();
        }
        
        @Override
        public void close() throws IOException {
            if (stream != System.out && stream != System.err)
                stream.close();
        }
    }
}
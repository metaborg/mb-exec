package org.spoofax.interpreter.library;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * An IO Agent class that logs all console output.
 * 
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class LoggingIOAgent extends IOAgent {
    
    private final Writer stdout = new OutputStreamWriter(new LoggingOutputStream(System.out));
    
    private final Writer stderr = new OutputStreamWriter(new LoggingOutputStream(System.err));
    
    final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    
    public String getLog() {
    	return bytes.toString();
    }
    
    public void clearLog() {
        bytes.reset();
    }
    
    @Override
    public Writer getWriter(int fd) {
        switch (fd) {
            case CONST_STDOUT:
                return stdout;
            case CONST_STDERR:
                return stderr;
            default:
                return super.getWriter(fd);
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
            // UNDONE: closing console streams is asking for trouble
            // if (stream != System.out && stream != System.err)
            //    stream.close();
            stream.flush();
        }
    }
}

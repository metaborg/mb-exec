/**
 * 
 */
package org.spoofax.interpreter.library.ssl;

import static java.lang.Math.min;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Channel-based PushbackInputStream implementation.
 * 
 * @deprecated completely untested
 * 
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class ChannelPushbackInputStream extends PushbackInputStream {
    
    private static final int BUFFER_SIZE = 512;
    
    private final FileChannel channel;
    
    private final ByteBuffer buffer;
    
    private final byte[] pushbackStack;
    
    private int pushbackSize = 0;

    public ChannelPushbackInputStream(FileChannel channel, int bufferSize) {
        super(null);
        this.channel = channel;
        buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
        pushbackStack = new byte[bufferSize];
    }
    
    public ChannelPushbackInputStream(FileChannel channel) {
        this(channel, 1);
    }
    
    @Override
    public int read() throws IOException {
        if (pushbackSize > 0) 
            return pushbackStack[--pushbackSize];
        if (buffer.hasRemaining()) {
            return buffer.get();
        } else {
            if (channel.read(buffer) == -1)
                return -1;
            buffer.rewind();
            return buffer.get();
        }
    }
    
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int i = off;
        do {
            if (pushbackSize > 0) {
                b[i++] = pushbackStack[--pushbackSize];
            } else {
                if (!buffer.hasRemaining()) {
                    if (channel.read(buffer) == -1)
                        return i == off ? -1 : i - off;
                    buffer.rewind();
                }
                int read = min(buffer.remaining(), len - i);
                buffer.get(b, i, i + read);
                i += read;
            }
        } while (i < len);
        return i - off;
    }
    
    @Override
    public void unread(byte[] b, int off, int len) throws IOException {
        for (int i = off; i < len; i++)
            unread(b[i]);
    }
    
    @Override
    public void unread(int b) throws IOException {
        if (pushbackSize == pushbackStack.length)
            throw new IOException("Pushback buffer is full");
        pushbackStack[pushbackSize++] = (byte) b;
    }
    
    @Override
    public synchronized void close() throws IOException {
        channel.close();
    }
    
    @Override
    public synchronized void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

}

/*
 * Created on 11. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class RandomAccessInputStream extends InputStream {

    private final RandomAccessFile raf;
    
    public RandomAccessInputStream(RandomAccessFile raf) {
        this.raf = raf;
    }
    
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return raf.read(b, off, len);
    }
    
    @Override
    public int read() throws IOException {
        return raf.read();
    }
    
    @Override
    public void close() throws IOException {
        raf.close();
    }

}

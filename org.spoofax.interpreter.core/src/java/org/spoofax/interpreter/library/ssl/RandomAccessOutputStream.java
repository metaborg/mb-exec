/*
 * Created on 11. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class RandomAccessOutputStream extends OutputStream {

    private final RandomAccessFile raf;

    public RandomAccessOutputStream(RandomAccessFile raf) {
        this.raf = raf;
    }
    
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        raf.write(b, off, len);
    }
    
    @Override
    public void write(int b) throws IOException {
        raf.write(b);
    }
    
    @Override
    public void close() throws IOException {
        raf.close(); 
    }

}

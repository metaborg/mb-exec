/*
 * Created on 11. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class RandomAccessOutputStream extends OutputStream {

    private RandomAccessFile raf;

    RandomAccessOutputStream(RandomAccessFile raf) {
        this.raf = raf;
    }
    
    @Override
    public void write(int b) throws IOException {
        raf.write(b);
    }

}

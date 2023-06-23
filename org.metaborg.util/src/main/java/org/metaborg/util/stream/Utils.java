package org.metaborg.util.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Utils {
    public static void copy(InputStream in, OutputStream out) throws IOException {
        final byte[] buffer = new byte[8192];
        int n = in.read(buffer);
        while (n != -1) {
            out.write(buffer, 0, n);
            n = in.read(buffer);
        }
    }
}

package org.metaborg.util.stream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.function.Consumer;

public class OnCloseByteArrayOutputStream extends ByteArrayOutputStream {
    final Consumer<ByteArrayOutputStream> action;

    public OnCloseByteArrayOutputStream(Consumer<ByteArrayOutputStream> action) {
        this.action = action;
    }

    @Override public void close() throws IOException {
        action.accept(this);
        super.close();
    }
}

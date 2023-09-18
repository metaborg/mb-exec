package org.metaborg.util.file;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.vfs2.FileObject;

public class CollectionFileAccess implements IFileAccess {
    private final Collection<FileObject> reads = new ArrayList<>();
    private final Collection<FileObject> writes = new ArrayList<>();


    public Iterable<FileObject> reads() {
        return reads;
    }

    public Iterable<FileObject> writes() {
        return writes;
    }


    @Override public void read(FileObject file) {
        reads.add(file);
    }

    @Override public void write(FileObject file) {
        writes.add(file);
    }
}

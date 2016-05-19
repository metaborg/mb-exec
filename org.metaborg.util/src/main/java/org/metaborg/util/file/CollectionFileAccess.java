package org.metaborg.util.file;

import java.util.Collection;

import org.apache.commons.vfs2.FileObject;

import com.google.common.collect.Lists;

public class CollectionFileAccess implements IFileAccess {
    private final Collection<FileObject> reads = Lists.newArrayList();
    private final Collection<FileObject> writes = Lists.newArrayList();


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

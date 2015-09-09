package org.metaborg.util.file;

import java.util.Collection;

import org.apache.commons.vfs2.FileObject;

import com.google.common.collect.Lists;

public class FileAccess {
    private final Collection<FileObject> reads = Lists.newLinkedList();
    private final Collection<FileObject> writes = Lists.newLinkedList();


    public Iterable<FileObject> reads() {
        return reads;
    }

    public Iterable<FileObject> writes() {
        return writes;
    }


    public void addRead(FileObject file) {
        reads.add(file);
    }

    public void addWrite(FileObject file) {
        writes.add(file);
    }
}

package org.metaborg.util.resource;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelectInfo;

public class DefaultFileSelectInfo implements FileSelectInfo {
    private final FileObject baseFolder;
    private final FileObject file;
    private final int depth;


    public DefaultFileSelectInfo(FileObject baseFolder, FileObject file, int depth) {
        this.baseFolder = baseFolder;
        this.file = file;
        this.depth = depth;
    }


    @Override public FileObject getBaseFolder() {
        return baseFolder;
    }

    @Override public FileObject getFile() {
        return file;
    }

    @Override public int getDepth() {
        return depth;
    }
}

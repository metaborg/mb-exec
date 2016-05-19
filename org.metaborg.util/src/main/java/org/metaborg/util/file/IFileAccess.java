package org.metaborg.util.file;

import org.apache.commons.vfs2.FileObject;

public interface IFileAccess {
    void read(FileObject file);

    void write(FileObject file);
}

package org.metaborg.util.file;

import org.apache.commons.vfs2.FileObject;

/**
 * Interface for registering file access.
 */
public interface IFileAccess {
    /**
     * Indicate that given file, or (non-recursive) contents of given directory, are going to be read. Call before
     * reading.
     * 
     * @param resource
     *            File or directory going to be read.
     */
    void read(FileObject resource);

    /**
     * Indicate that given file has been written to. Call after writing.
     * 
     * @param file
     *            File that has been written to.
     */
    void write(FileObject file);
}
